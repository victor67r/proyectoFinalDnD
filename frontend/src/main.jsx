import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import GeometricBackdrop from './components/GeometricBackdrop.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <div className="app-shell">
      <GeometricBackdrop />
      <div className="app-content">
        <App />
      </div>
    </div>
  </StrictMode>,
)
