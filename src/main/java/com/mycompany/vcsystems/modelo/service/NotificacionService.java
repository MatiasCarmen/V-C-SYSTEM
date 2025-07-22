package com.mycompany.vcsystems.modelo.service;

import com.mycompany.vcsystems.modelo.entidades.Notificacion;
import com.mycompany.vcsystems.modelo.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public Notificacion enviarNotificacion(String destinatario, String mensaje) {
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje(mensaje);
        notificacion.setDestinatario(destinatario);
        notificacion.setLeido(false);
        notificacion = notificacionRepository.save(notificacion);

        // Enviar por WebSocket al canal del usuario
        messagingTemplate.convertAndSend("/topic/notificaciones/" + destinatario, notificacion);

        return notificacion;
    }

    public List<Notificacion> obtenerNoLeidas(String destinatario) {
        return notificacionRepository.findByDestinatarioAndLeidoFalse(destinatario);
    }

    public void marcarComoLeida(Long id) {
        notificacionRepository.findById(id).ifPresent(n -> {
            n.setLeido(true);
            notificacionRepository.save(n);
        });
    }

    public void notificarIncidencia(String destinatario, String asunto, String mensaje) {
        Notificacion notificacion = new Notificacion();
        notificacion.setDestinatario(destinatario);
        notificacion.setMensaje(asunto + ": " + mensaje);
        notificacion.setLeido(false);
        notificacion.setFechaEnvio(LocalDateTime.now());

        notificacionRepository.save(notificacion);

        // Notificación por WebSocket (si lo usas)
        messagingTemplate.convertAndSend("/topic/notificaciones/" + destinatario, notificacion);
    }

}
