// logica para crear nuevos usuarios

document.addEventListener('DOMContentLoaded', function() {
    setupRegisterClientForm();
});

function setupRegisterClientForm() {
    const form = document.getElementById('registerClientForm');
    const submitBtn = document.getElementById('registerClientSubmitBtn');


    if (!submitBtn.querySelector('.loading-spinner')) {
        const spinner = document.createElement('span');
        spinner.classList.add('spinner-border', 'spinner-border-sm', 'me-2', 'd-none', 'loading-spinner');
        spinner.setAttribute('role', 'status');
        spinner.setAttribute('aria-hidden', 'true');
        submitBtn.prepend(spinner);

        const btnText = document.createElement('span');
        btnText.classList.add('btn-text');
        btnText.textContent = submitBtn.textContent;
        submitBtn.textContent = '';
        submitBtn.appendChild(btnText);
    }


    form.addEventListener('submit', async function(e) {
        e.preventDefault();

        if (!validateRegisterForm()) {
            securityManager.showAlert('Por favor, completa todos los campos requeridos correctamente.', 'warning');
            return;
        }

        const formData = new FormData(form);
        const data = Object.fromEntries(formData.entries());


        setRegisterLoading(true);

        try {
            const response = await securityManager.makeRequest('/api/auth/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });

            if (response.ok) {
                // Mostrar alerta de éxito más prominente
                showSuccessAlert();

                // Limpiar formulario
                form.reset();

                form.querySelectorAll('.form-control').forEach(field => {
                    field.classList.remove('is-valid', 'is-invalid');
                    const invalidFeedback = field.nextElementSibling;
                    if (invalidFeedback && invalidFeedback.classList.contains('invalid-feedback')) {
                        invalidFeedback.style.display = 'none';
                    }
                });

                // Redirigir después de 3 segundos para que el usuario vea la alerta
                setTimeout(() => {
                    window.location.href = 'login.html';
                }, 3000);
            } else {
                const errorData = await response.json();
                securityManager.showAlert(errorData.message || 'Error al registrar cliente.', 'danger');
            }
        } catch (error) {
            securityManager.showAlert('Error de red o inesperado al registrar cliente.', 'danger');
        } finally {

            setRegisterLoading(false);
        }
    });


    form.querySelectorAll('input[required], select[required], textarea[required]').forEach(field => {
        field.addEventListener('blur', function() {
            validateRegisterField(this);
        });

        if (field.type === 'email' || field.type === 'text') {
            field.addEventListener('input', function() {
                validateRegisterField(this);
            });
        }
    });
}

function validateRegisterForm() {
    const form = document.getElementById('registerClientForm');
    let isFormValid = true;
    form.querySelectorAll('input[required], select[required], textarea[required]').forEach(field => {
        if (!validateRegisterField(field)) {
            isFormValid = false;
        }
    });
    return isFormValid;
}

function validateRegisterField(field) {
    const value = field.value.trim();
    const invalidFeedback = field.nextElementSibling;
    let isValid = true;

    if (field.hasAttribute('required') && value === '') {
        isValid = false;
    } else if (field.type === 'email') {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(value)) {
            isValid = false;
        }
    }


    if (isValid) {
        field.classList.remove('is-invalid');
        field.classList.add('is-valid');
        if (invalidFeedback && invalidFeedback.classList.contains('invalid-feedback')) {
            invalidFeedback.style.display = 'none';
        }
    } else {
        field.classList.remove('is-valid');
        field.classList.add('is-invalid');
        if (invalidFeedback && invalidFeedback.classList.contains('invalid-feedback')) {
            invalidFeedback.style.display = 'block';
        }
    }

    return isValid;
}

function setRegisterLoading(loading) {
    const submitBtn = document.getElementById('registerClientSubmitBtn');
    const btnText = submitBtn.querySelector('.btn-text');
    const spinner = submitBtn.querySelector('.loading-spinner');

    if (loading) {
        submitBtn.disabled = true;
        btnText.classList.add('d-none');
        spinner.classList.remove('d-none');
    } else {
        submitBtn.disabled = false;
        btnText.classList.remove('d-none');
        spinner.classList.add('d-none');
    }
}

// Función para mostrar alerta de éxito prominente
function showSuccessAlert() {

    const alertContainer = document.createElement('div');
    alertContainer.className = 'alert alert-success alert-dismissible fade show position-fixed';
    alertContainer.style.cssText = `
        top: 20px;
        left: 50%;
        transform: translateX(-50%);
        z-index: 9999;
        min-width: 400px;
        text-align: center;
        box-shadow: 0 4px 12px rgba(0,0,0,0.15);
        border: none;
        border-radius: 10px;
    `;

    alertContainer.innerHTML = `
        <div class="d-flex align-items-center">
            <i class="fas fa-check-circle me-3" style="font-size: 1.5rem; color: #198754;"></i>
            <div>
                <h5 class="alert-heading mb-1">¡Cliente Creado Exitosamente!</h5>
                <p class="mb-0">El cliente ha sido registrado en el sistema. Serás redirigido al login en 3 segundos...</p>
            </div>
            <button type="button" class="btn-close ms-auto" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    `;

    // Agregar al body
    document.body.appendChild(alertContainer);

    // Auto-remover después de 3 segundos
    setTimeout(() => {
        if (alertContainer.parentNode) {
            alertContainer.remove();
        }
    }, 3000);

    // También mostrar el toast normal como respaldo
    securityManager.showAlert('Cliente registrado exitosamente. Redirigiendo al login...', 'success');
}