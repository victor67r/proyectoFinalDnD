import { useState } from 'react'
import './App.css'

function App() {
  const [modo, setModo] = useState('login')
  const esRegistro = modo === 'registro'

  const [formulario, setFormulario] = useState({
    nombre: '',
    email: '',
    password: '',
    confirmarPassword: '',
  })

  const [errores, setErrores] = useState({})
  const [mensaje, setMensaje] = useState('')

  function cambiarCampo(evento) {
    const { name, value } = evento.target

    setFormulario((actual) => ({
      ...actual,
      [name]: value,
    }))

    setErrores((actual) => ({
      ...actual,
      [name]: '',
    }))

    setMensaje('')
  }

  function cambiarModo(nuevoModo) {
    setModo(nuevoModo)
    setErrores({})
    setMensaje('')
  }

  function enviarFormulario(evento) {
    evento.preventDefault()

    const nuevosErrores = {}
    const emailLimpio = formulario.email.trim()
    const formatoEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/

    if (!emailLimpio) {
      nuevosErrores.email = 'Introduce tu correo electrónico.'
    } else if (!formatoEmail.test(emailLimpio)) {
      nuevosErrores.email = 'Escribe un correo electrónico válido.'
    }

    if (!formulario.password) {
      nuevosErrores.password = 'Introduce tu contraseña.'
    }

    if (esRegistro && !formulario.nombre.trim()) {
      nuevosErrores.nombre = 'Introduce tu nombre de aventurero.'
    }

    if (
      esRegistro &&
      formulario.password !== formulario.confirmarPassword
    ) {
      nuevosErrores.confirmarPassword = 'Las contraseñas no coinciden.'
    }

    setErrores(nuevosErrores)

    if (Object.keys(nuevosErrores).length > 0) {
      setMensaje('')
      return
    }

    setMensaje('Los datos son válidos. Aún falta conectarlos con el servidor.')
  }

  return (
    <main className="auth-page">
      <section className="auth-card">
        <header className="auth-header">
          <div className="auth-emblem" aria-hidden="true">✦</div>
          <p className="auth-kicker">Crónicas de la mesa</p>
          <h1>{esRegistro ? 'Crea tu cuenta' : 'Bienvenido de nuevo'}</h1>
          <p className="auth-description">
            {esRegistro
              ? 'Prepara tu personaje y únete a la aventura.'
              : 'Tu próxima aventura te está esperando.'}
          </p>
        </header>

        <div className="auth-tabs" role="tablist" aria-label="Acceso">
          <button
            type="button"
            role="tab"
            aria-selected={!esRegistro}
            className={!esRegistro ? 'auth-tab active' : 'auth-tab'}
            onClick={() => cambiarModo('login')}
          >
            Iniciar sesión
          </button>

          <button
            type="button"
            role="tab"
            aria-selected={esRegistro}
            className={esRegistro ? 'auth-tab active' : 'auth-tab'}
            onClick={() => cambiarModo('registro')}
          >
            Crear cuenta
          </button>
        </div>

        <form className="auth-form" onSubmit={enviarFormulario} noValidate>
          {esRegistro && (
            <div className="form-field">
              <label htmlFor="nombre">Nombre de aventurero</label>
              <input
                id="nombre"
                name="nombre"
                type="text"
                value={formulario.nombre}
                onChange={cambiarCampo}
                autoComplete="name"
                placeholder="Cómo te llamas"
                aria-invalid={Boolean(errores.nombre)}
                aria-describedby={errores.nombre ? 'nombre-error' : undefined}
              />
              {errores.nombre && (
                <span className="field-error" id="nombre-error">
                  {errores.nombre}
                </span>
              )}
            </div>
          )}

          <div className="form-field">
            <label htmlFor="email">Correo electrónico</label>
            <input
              id="email"
              name="email"
              type="email"
              value={formulario.email}
              onChange={cambiarCampo}
              autoComplete="email"
              placeholder="aventurero@ejemplo.com"
              aria-invalid={Boolean(errores.email)}
              aria-describedby={errores.email ? 'email-error' : undefined}
            />
            {errores.email && (
              <span className="field-error" id="email-error">
                {errores.email}
              </span>
            )}
          </div>

          <div className="form-field">
            <label htmlFor="password">Contraseña</label>
            <input
              id="password"
              name="password"
              type="password"
              value={formulario.password}
              onChange={cambiarCampo}
              autoComplete={esRegistro ? 'new-password' : 'current-password'}
              placeholder="Introduce tu contraseña"
              aria-invalid={Boolean(errores.password)}
              aria-describedby={errores.password ? 'password-error' : undefined}
            />
            {errores.password && (
              <span className="field-error" id="password-error">
                {errores.password}
              </span>
            )}
          </div>

          {esRegistro && (
            <div className="form-field">
              <label htmlFor="confirmarPassword">Repite la contraseña</label>
              <input
                id="confirmarPassword"
                name="confirmarPassword"
                type="password"
                value={formulario.confirmarPassword}
                onChange={cambiarCampo}
                autoComplete="new-password"
                placeholder="Escribe la contraseña otra vez"
                aria-invalid={Boolean(errores.confirmarPassword)}
                aria-describedby={
                  errores.confirmarPassword
                    ? 'confirmarPassword-error'
                    : undefined
                }
              />
              {errores.confirmarPassword && (
                <span className="field-error" id="confirmarPassword-error">
                  {errores.confirmarPassword}
                </span>
              )}
            </div>
          )}

          <button className="auth-submit" type="submit">
            {esRegistro ? 'Crear cuenta' : 'Entrar en la partida'}
          </button>

          {mensaje && (
            <p className="auth-message" role="status">
              {mensaje}
            </p>
          )}
        </form>

        <p className="auth-footer">
          {esRegistro
            ? 'Al crear una cuenta, comienza tu aventura.'
            : '¿Aún no tienes cuenta? Usa “Crear cuenta” para registrarte.'}
        </p>
      </section>
    </main>
  )
}

export default App