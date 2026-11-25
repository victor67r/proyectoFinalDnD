export default function GeometricBackdrop() {
  return (
    <div className="geometric-backdrop" aria-hidden="true">
      <svg viewBox="0 0 1440 900" preserveAspectRatio="xMidYMid slice" focusable="false">
        <defs>
          <linearGradient id="geo-metal" x1="0" y1="0" x2="1" y2="1">
            <stop offset="0" stopColor="#fff" stopOpacity=".8" />
            <stop offset=".5" stopColor="#aaa" stopOpacity=".2" />
            <stop offset="1" stopColor="#fff" stopOpacity=".65" />
          </linearGradient>
          <radialGradient id="geo-glow">
            <stop stopColor="#fff" stopOpacity=".4" />
            <stop offset="1" stopColor="#fff" stopOpacity="0" />
          </radialGradient>
          <filter id="geo-soft-glow" x="-100%" y="-100%" width="300%" height="300%">
            <feGaussianBlur stdDeviation="9" />
          </filter>
        </defs>

        <g className="geo-lines">
          <path d="M0 690 350 280 670 550 980 110 1440 430M110 900 520 520 820 800 1170 420 1440 690" />
          <path d="M0 170 320 400 620 120 980 460 1240 180 1440 330M100 0 420 220 760 0M900 900 1210 630 1440 780" />
          <circle cx="350" cy="280" r="3" />
          <circle cx="670" cy="550" r="3" />
          <circle cx="980" cy="110" r="3" />
          <circle cx="1170" cy="420" r="3" />
        </g>

        <g transform="translate(260 250)">
          <circle r="126" className="geo-ring geo-ring--dashed" />
          <g className="geo-orbit geo-orbit--slow">
            <circle r="88" className="geo-ring" />
            <rect x="-58" y="-58" width="116" height="116" rx="8" className="geo-shape" transform="rotate(45)" />
            <path d="M0-102 88 50H-88Z" className="geo-shape geo-shape--bright" />
            <path d="m-42 12 42-72 42 72-42 72z" className="geo-shape" />
            <circle cx="88" cy="0" r="7" className="geo-dot" />
          </g>
          <circle r="164" fill="url(#geo-glow)" filter="url(#geo-soft-glow)" className="geo-halo" />
        </g>

        <g transform="translate(1170 280)">
          <g className="geo-orbit geo-orbit--reverse">
            <circle r="112" className="geo-ring" />
            <circle r="148" className="geo-ring geo-ring--dashed" />
            <polygon points="0,-92 80,46 -80,46" className="geo-shape geo-shape--bright" />
            <rect x="-48" y="-48" width="96" height="96" className="geo-shape" transform="rotate(22)" />
            <path d="M-112 0h224M0-112v224" className="geo-shape" />
          </g>
          <circle cx="0" cy="0" r="5" className="geo-dot" />
        </g>

        <g transform="translate(1010 720)">
          <g className="geo-float geo-float--one">
            <path d="m0-118 104 60v116L0 118l-104-60V-58z" className="geo-shape geo-shape--bright" />
            <path d="m0-72 64 36v72L0 72l-64-36v-72z" className="geo-shape" />
            <circle r="24" className="geo-ring" />
            <circle cx="104" cy="-58" r="6" className="geo-dot" />
          </g>
        </g>

        <g transform="translate(460 730)">
          <g className="geo-float geo-float--two">
            <polygon points="0,-90 78,-45 78,45 0,90 -78,45 -78,-45" className="geo-shape" />
            <polygon points="0,-52 45,-26 45,26 0,52 -45,26 -45,-26" className="geo-shape geo-shape--bright" />
            <path d="m-78-45 78 45 78-45M0 0v90" className="geo-shape" />
          </g>
        </g>
      </svg>
    </div>
  )
}
