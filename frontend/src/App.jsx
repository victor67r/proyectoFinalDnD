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

  function cambiarCampo(evento) {
    const { name, value } = evento.target

    setFormulario((actual) => ({
      ...actual,
      [name]: value,
    }))
  }

  function enviarFormulario(evento) {
    evento.preventDefault()
    // Añadiremos validaciones y conexión con el backend después.
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
            onClick={() => setModo('login')}
          >
            Iniciar sesión
          </button>

          <button
            type="button"
            role="tab"
            aria-selected={esRegistro}
            className={esRegistro ? 'auth-tab active' : 'auth-tab'}
            onClick={() => setModo('registro')}
          >
            Crear cuenta
          </button>
        </div>

        <form className="auth-form" onSubmit={enviarFormulario}>
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
                required
              />
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
              required
            />
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
              required
            />
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
                required
              />
            </div>
          )}

          <button className="auth-submit" type="submit">
            {esRegistro ? 'Crear cuenta' : 'Entrar en la partida'}
          </button>
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