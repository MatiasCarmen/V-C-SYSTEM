package com.mycompany.vcsystems.api.controlador;

import com.mycompany.vcsystems.modelo.entidades.Notificacion;
import com.mycompany.vcsystems.modelo.service.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionControlador {

    private final NotificacionService notificacionService;

    // 🔔 Endpoint para enviar una notificación (por ejemplo desde Postman o desde eventos)
    @PostMapping("/enviar")
    public void enviar(
            @RequestParam String destinatario,
            @RequestParam String asunto,
            @RequestParam String mensaje) {
        notificacionService.notificarIncidencia(destinatario, asunto, mensaje);
    }

    // 📩 Endpoint para obtener las notificaciones no leídas
    @GetMapping("/pendientes/{correo}")
    public List<Notificacion> obtenerNoLeidas(@PathVariable String correo) {
        return notificacionService.obtenerNoLeidas(correo);
    }

    // ✅ Endpoint para marcar una notificación como leída
    @PutMapping("/leer/{id}")
    public void marcarComoLeida(@PathVariable Long id) {
        notificacionService.marcarComoLeida(id);
    }
}
