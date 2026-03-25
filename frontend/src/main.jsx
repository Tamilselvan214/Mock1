import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import Auth from './pages/Auth.jsx'


const router = createBrowserRouter([
  {
    path:"/",
    index: true,
    element:<App/>
  },
  {
    path:"/auth",
    element:<Auth/>
  },
])


createRoot(document.getElementById('root')).render(
  <StrictMode>
    <RouterProvider router={router}/>
  </StrictMode>,
)
