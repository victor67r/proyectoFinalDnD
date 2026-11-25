# vtt-frontend

Desde esta carpeta (`frontend`):

```powershell
npm install
npm run dev
```

Abre la dirección que muestra Vite (normalmente http://localhost:5173). Tras
iniciar sesión, elige «Crear sala como DM» para abrir el panel de juego.
El proxy de desarrollo reenvía las peticiones `/api` a Spring Boot en el puerto
8080. Si el backend no está disponible, los componentes de juego usan datos de
prueba automáticamente. Para forzar ese modo, crea `.env` con
`VITE_USE_MOCK=true`.
