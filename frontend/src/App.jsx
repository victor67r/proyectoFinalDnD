import { useEffect, useRef, useState } from 'react'
import CharacterCard from './components/CharacterCard'
import InitiativeTracker from './components/InitiativeTracker'
import DiceRoller from './components/DiceRoller'

const ORDEN_INICIAL = ['personaje', 'goblin', 'iniciativa']
const CLAVE = 'orden-paneles'
const PANELES = [
  { id: 'personaje', nombre: 'Personaje' },
  { id: 'goblin', nombre: 'Goblin' },
  { id: 'iniciativa', nombre: 'Iniciativa' },
  { id: 'dados', nombre: 'Dados' },
]

function cargarOrden() {
  try {
    const o = JSON.parse(localStorage.getItem(CLAVE))
    if (!Array.isArray(o)) return ORDEN_INICIAL
    const migrados = o.map((id) => {
      if (id === 'aliado') return 'personaje'
      if (id.startsWith('enemigo-')) return 'goblin'
      return id
    })
    const permitidos = new Set(PANELES.map(({ id }) => id))
    const unicos = [...new Set(migrados.filter((id) => permitidos.has(id)))]
    return unicos.length ? unicos : ORDEN_INICIAL
  } catch { /* sin orden guardado */ }
  return ORDEN_INICIAL
}

function guardarOrden(orden) {
  try { localStorage.setItem(CLAVE, JSON.stringify(orden)) } catch { /* ignorar */ }
}

// Posición destino al mover con teclado dentro de la rejilla 2x2
function destino(i, tecla) {
  if (tecla === 'ArrowLeft' && i % 2 === 1) return i - 1
  if (tecla === 'ArrowRight' && i % 2 === 0) return i + 1
  if (tecla === 'ArrowUp' && i >= 2) return i - 2
  if (tecla === 'ArrowDown' && i < 2) return i + 2
  return null
}

export default function App() {
  const [orden, setOrden] = useState(cargarOrden)
  const [arrastrando, setArrastrando] = useState(null)
  const [sobre, setSobre] = useState(null)
  const [mostrarOpciones, setMostrarOpciones] = useState(false)
  const foco = useRef(null)

  useEffect(() => {
    if (foco.current) {
      document.querySelector(`[data-asa="${foco.current}"]`)?.focus()
      foco.current = null
    }
  }, [orden])

  const intercambiar = (a, b) => {
    if (!a || !b || a === b) return
    const o = [...orden]
    const i = o.indexOf(a)
    const j = o.indexOf(b)
    ;[o[i], o[j]] = [o[j], o[i]]
    setOrden(o)
    guardarOrden(o)
  }

  const terminar = () => { setArrastrando(null); setSobre(null) }

  const anadirPanel = (id) => {
    const siguiente = [...orden, id]
    setOrden(siguiente)
    guardarOrden(siguiente)
    setMostrarOpciones(false)
  }

  const quitarPanel = (id) => {
    const siguiente = orden.filter((panelId) => panelId !== id)
    setOrden(siguiente)
    guardarOrden(siguiente)
  }

  const alTeclado = (e, id) => {
    const j = destino(orden.indexOf(id), e.key)
    if (j === null) return
    e.preventDefault()
    foco.current = id
    intercambiar(id, orden[j])
  }

  return (
    <main className="grid">
      <div className="controles-paneles">
        <button
          className="btn pequeno"
          type="button"
          aria-expanded={mostrarOpciones}
          aria-controls="opciones-paneles"
          onClick={() => setMostrarOpciones((mostrar) => !mostrar)}
        >
          {mostrarOpciones ? 'Cerrar' : '+ Añadir panel'}
        </button>
        {mostrarOpciones && (
          <div className="opciones-paneles" id="opciones-paneles" aria-label="Paneles disponibles">
            {PANELES.filter(({ id }) => !orden.includes(id)).map(({ id, nombre }) => (
              <button key={id} className="btn pequeno" type="button" onClick={() => anadirPanel(id)}>
                + {nombre}
              </button>
            ))}
            {PANELES.every(({ id }) => orden.includes(id)) && (
              <span className="demo">Ya están añadidos todos los paneles.</span>
            )}
          </div>
        )}
      </div>
      {orden.map((id) => (
        <div
          key={id}
          className={`celda${arrastrando === id ? ' arrastrando' : ''}${sobre === id && arrastrando !== id ? ' destino' : ''}`}
          onDragOver={(e) => { e.preventDefault(); setSobre(id) }}
          onDrop={(e) => { e.preventDefault(); intercambiar(arrastrando, id); terminar() }}
        >
          <div
            className="asa"
            role="button"
            tabIndex={0}
            draggable
            data-asa={id}
            aria-label={`Mover panel ${id}. Arrastra o usa las flechas del teclado.`}
            title="Arrastra para intercambiar de sitio"
            onDragStart={(e) => {
              e.dataTransfer.effectAllowed = 'move'
              e.dataTransfer.setData('text/plain', id)
              e.dataTransfer.setDragImage(e.currentTarget.parentElement, 24, 24)
              setArrastrando(id)
            }}
            onDragEnd={terminar}
            onKeyDown={(e) => alTeclado(e, id)}
          >
            <svg viewBox="0 0 24 8" aria-hidden="true">
              {[4, 12, 20].map((x) => (<g key={x}><circle cx={x} cy="2" r="1.4" /><circle cx={x} cy="6" r="1.4" /></g>))}
            </svg>
          </div>
          <button
            className="btn quitar-panel"
            type="button"
            aria-label={`Quitar panel ${PANELES.find((panel) => panel.id === id)?.nombre}`}
            title="Quitar panel"
            onClick={() => quitarPanel(id)}
          >
          </button>
          {id === 'personaje' && (
            <CharacterCard tipo="personajes" bando="aliado" fichaId={1} colorId="personaje" />
          )}
          {id === 'goblin' && (
            <CharacterCard tipo="monstruos" bando="enemigo" fichaId={1} colorId="goblin" />
          )}
          {id === 'iniciativa' && <InitiativeTracker />}
          {id === 'dados' && <DiceRoller />}
        </div>
      ))}
    </main>
  )
}
