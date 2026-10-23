import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// El proxy reenvía /api/* a Spring Boot: el navegador cree que es el mismo origen,
// así que no hace falta configurar CORS en Java durante el desarrollo.
export default defineConfig({
  plugins: [react()],
  server: { proxy: { '/api': 'http://localhost:8080' } },
})
