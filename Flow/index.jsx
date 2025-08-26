
import React from 'react'
import ReactDOM from 'react-dom/client'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Level1 from './Levels/Level1'
import Level2 from './Levels/Level2'
import Level3 from './Levels/Level3'
import Level4 from './Levels/Level4'
import Level5 from './Levels/Level5'
import Level6 from './Levels/Level6'
import StartScreen from './StartScreen'

ReactDOM.createRoot(document.getElementById('root')).render(
	<React.StrictMode>
		<BrowserRouter>
			<Routes>
				<Route path="/" element={<StartScreen />} />
				<Route path="/level1" element={<Level1 />} />
				<Route path="/level2" element={<Level2 />} />
				<Route path="/level3" element={<Level3 />} />"
				<Route path="/level4" element={<Level4 />} />
				<Route path="/level5" element={<Level5 />} />
				<Route path="/level6" element={<Level6 />} />
			</Routes>
		</BrowserRouter>
	</React.StrictMode>
)
