import { useState } from 'react'
import { ordenarIniciativa } from '../api'

export default function InitiativeTracker() {
  const [lista, setLista] = useState([])
  const [nombre, setNombre] = useState('')
  const [tirada, setTirada] = useState('')
  const [mock, setMock] = useState(false)
  const [ordenando, setOrdenando] = useState(false)

  const anadir = (e) => {
    e.preventDefault()
    if (!nombre.trim() || tirada === '') return
    setLista((l) => [...l, { id: crypto.randomUUID(), nombre: nombre.trim(), tirada: Number(tirada) }])
    setNombre('')
    setTirada('')
  }

  const ordenar = async () => {
    setOrdenando(true)
    const res = await ordenarIniciativa(lista)
    setLista(res.lista.map((p, i) => ({ id: p.id ?? `${p.nombre}-${i}`, ...p })))
    setMock(res.mock)
    setOrdenando(false)
  }

  return (
    <section className="panel iniciativa">
      <h2>Iniciativa</h2>

      <form className="fila" onSubmit={anadir}>
        <input type="text" placeholder="Nombre" value={nombre} onChange={(e) => setNombre(e.target.value)} aria-label="Nombre" />
        <input className="num" type="number" placeholder="Tirada" value={tirada} onChange={(e) => setTirada(e.target.value)} aria-label="Tirada" />
        <button className="btn" type="submit">Añadir</button>
      </form>

      {lista.length === 0 ? (
        <p className="vacio">Añade a los participantes del combate.</p>
      ) : (
        <ul className="lista">
          {lista.map((p) => (
            <li key={p.id}>
              <span>{p.nombre}</span>
              <strong>{p.tirada}</strong>
            </li>
          ))}
        </ul>
      )}

      <button className="btn grande primario" onClick={ordenar} disabled={lista.length < 2 || ordenando}>
        Ordenar Iniciativa
      </button>
      {mock && <p className="demo">Orden calculado en local (datos de prueba)</p>}
    </section>
  )
}
