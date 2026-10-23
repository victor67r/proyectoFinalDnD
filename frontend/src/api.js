// Toda la comunicación con Spring Boot vive aquí.
// Si el backend no responde, se usan datos de prueba (mock) automáticamente,
// así que la integración real no requiere cambiar ningún componente.
// Para forzar el modo demo: crea .env con VITE_USE_MOCK=true

const FORCE_MOCK = import.meta.env.VITE_USE_MOCK === 'true'

async function llamar(url, opciones, mock) {
  if (!FORCE_MOCK) {
    try {
      const res = await fetch(url, opciones)
      if (res.ok) return { data: await res.json(), mock: false }
    } catch {
      /* backend no disponible: caemos al mock */
    }
  }
  return { data: mock(), mock: true }
}

// ---- Fichas (Marcelino) ----
const MOCKS_FICHA = {
  personajes: { nombre: 'Elian', raza: 'Drow', clase: 'Mago', vida: 25, vidaMaxima: 25, ca: 13 },
  monstruos: { nombre: 'Goblin 1', raza: 'Humanoide', clase: 'Goblin', vida: 7, vidaMaxima: 7, ca: 15 },
}

// Tolera nombres de campo distintos hasta acordar el JSON definitivo con Marcelino.
const normalizarFicha = (d) => ({
  nombre: d.nombre ?? 'Sin nombre',
  raza: d.raza ?? d.tipo ?? '',
  clase: d.clase ?? '',
  vida: d.vida ?? d.hp ?? 0,
  vidaMaxima: d.vidaMaxima ?? d.vidaMax ?? d.vida ?? d.hp ?? 1,
  ca: d.claseArmadura ?? d.ca ?? d.armadura ?? 10,
})

export async function getFicha(tipo, id = 1) {
  const { data, mock } = await llamar(
    `/api/${tipo}/${id}`,
    undefined,
    () => ({ ...MOCKS_FICHA[tipo], ...(tipo === 'monstruos' ? { nombre: `Goblin ${id}` } : {}) }),
  )
  return { ficha: normalizarFicha(data), mock }
}

// ---- Iniciativa (Víctor) ----
const ordenarLocal = (lista) => [...lista].sort((a, b) => b.tirada - a.tirada)

export async function ordenarIniciativa(lista) {
  const { data, mock } = await llamar(
    '/api/iniciativa/ordenar',
    {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(lista.map(({ nombre, tirada }) => ({ nombre, tirada }))),
    },
    () => ordenarLocal(lista),
  )
  const ordenada = Array.isArray(data) ? data : ordenarLocal(lista)
  return { lista: ordenada.map((p) => ({ ...p, tirada: p.tirada ?? p.iniciativa })), mock }
}

// ---- Dados (Víctor) ----
export async function tirarDado(caras) {
  const { data, mock } = await llamar(`/api/dados/${caras}`, undefined, () => Math.ceil(Math.random() * caras))
  const valor = typeof data === 'number' ? data : (data.resultado ?? data.valor)
  return { valor, mock }
}
