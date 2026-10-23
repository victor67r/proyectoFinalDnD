import { useEffect, useRef, useState } from 'react'
import { tirarDado } from '../api'

const DADOS = [20, 8, 6]

export default function DiceRoller() {
  const [log, setLog] = useState([])
  const [mock, setMock] = useState(false)
  const fin = useRef(null)

  useEffect(() => {
    fin.current?.scrollIntoView({ block: 'end' })
  }, [log])

  const tirar = async (caras) => {
    const { valor, mock } = await tirarDado(caras)
    setMock(mock)
    const especial = caras === 20 && valor === 20 ? 'crit' : caras === 20 && valor === 1 ? 'pifia' : ''
    setLog((l) => [...l, { id: crypto.randomUUID(), texto: `Tirada d${caras}: Has sacado un ${valor}`, especial }])
  }

  return (
    <section className="panel dados">
      <h2>Dados</h2>

      <div className="botones tres">
        {DADOS.map((c) => (
          <button key={c} className="btn dado" onClick={() => tirar(c)}>d{c}</button>
        ))}
      </div>

      <div className="registro" aria-live="polite">
        {log.length === 0 && <p className="vacio">Aún no hay tiradas.</p>}
        {log.map((l) => (
          <p key={l.id} className={l.especial}>{l.texto}</p>
        ))}
        <div ref={fin} />
      </div>
      {mock && <p className="demo">Tiradas simuladas en local (datos de prueba)</p>}
    </section>
  )
}
