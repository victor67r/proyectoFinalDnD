import { useEffect, useState } from 'react'
import { getFicha } from '../api'

const COLOR_PREDETERMINADO = '#f2f2f2'

function cargarColor(colorId) {
  try {
    return localStorage.getItem(`color-ficha-${colorId}`) || COLOR_PREDETERMINADO
  } catch {
    return COLOR_PREDETERMINADO
  }
}

function Escudo({ valor }) {
  return (
    <div className="escudo" title="Clase de Armadura">
      <svg viewBox="0 0 24 28" aria-hidden="true">
        <path d="M12 1 2 5v8c0 6.5 4.2 11.2 10 14 5.8-2.8 10-7.5 10-14V5L12 1Z" />
      </svg>
      <span>{valor}</span>
    </div>
  )
}

export default function CharacterCard({ tipo, bando, fichaId, colorId }) {
  const [ficha, setFicha] = useState(null)
  const [mock, setMock] = useState(false)
  const [color, setColor] = useState(() => cargarColor(colorId))

  useEffect(() => {
    getFicha(tipo, fichaId).then(({ ficha, mock }) => {
      setFicha(ficha)
      setMock(mock)
    })
  }, [tipo, fichaId])

  const cambiarColor = (nuevoColor) => {
    setColor(nuevoColor)
    try { localStorage.setItem(`color-ficha-${colorId}`, nuevoColor) } catch { /* ignorar */ }
  }

  // TODO: cuando Marcelino exponga un PUT/PATCH, persistir aquí la vida.
  const cambiarVida = (delta) =>
    setFicha((f) => ({ ...f, vida: Math.min(f.vidaMaxima, Math.max(0, f.vida + delta)) }))

  if (!ficha) return <section className={`panel ${bando}`}><p className="vacio">Cargando ficha…</p></section>

  const pct = Math.round((ficha.vida / ficha.vidaMaxima) * 100)
  const estado = pct <= 25 ? 'critico' : pct <= 50 ? 'herido' : 'sano'

  return (
    <section className={`panel ${bando}`} style={{ '--acento': color }}>
      <header className="cabecera">
        <div>
          <h2>{ficha.nombre}</h2>
          <p className="sub">{[ficha.raza, ficha.clase].filter(Boolean).join(' · ')}</p>
        </div>
        <div className="herramientas-ficha">
          <label className="selector-color" title="Cambiar color de la ficha">
            <input
              type="color"
              value={color}
              onChange={(e) => cambiarColor(e.target.value)}
              aria-label={`Color de ${ficha.nombre}`}
            />
          </label>
          <Escudo valor={ficha.ca} />
        </div>
      </header>

      <div className="vida">
        <div className="vida-texto">
          <span>Puntos de vida</span>
          <strong>{ficha.vida} / {ficha.vidaMaxima}</strong>
        </div>
        <div className="barra" role="progressbar" aria-valuemin={0} aria-valuemax={ficha.vidaMaxima} aria-valuenow={ficha.vida}>
          <div className={`relleno ${estado}`} style={{ width: `${pct}%`, '--acento': color }} />
        </div>
      </div>

      <div className="botones">
        <button className="btn grande" onClick={() => cambiarVida(1)}>+1 HP</button>
        <button className="btn grande peligro" onClick={() => cambiarVida(-1)}>−1 HP</button>
      </div>

      {mock && <p className="demo">Datos de prueba</p>}
    </section>
  )
}
