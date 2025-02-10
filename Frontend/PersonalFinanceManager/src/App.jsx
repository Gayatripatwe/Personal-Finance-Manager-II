import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginForm from "../src/Pages/LoginForm";
import Dashboard from "../src/Pages/Dashboard";
import LandingPage from "./Pages/LandingPage";
import Budget from "./Pages/Budget";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LandingPage />} /> {/* Default Route */}
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/Budget" element={<Budget/>}/>
      </Routes>
    </Router>
  );
}

export default App;
