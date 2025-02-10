import React from "react";
import { useNavigate } from "react-router-dom";
import Button from "react-bootstrap/Button";
import "./Sidebar.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTree } from "@fortawesome/free-solid-svg-icons";

function Sidebar() {
  const navigate = useNavigate();

  return (
    <div className="sidebar">
      <h2>Your Budget</h2>
      <ul>
        <li onClick={() => navigate("/budget")}>🏦 Budget</li>
        <li onClick={() => navigate("/income")}>💰 Income</li>
        <li onClick={() => navigate("/expenses")}>📉 Expenses</li>
        <li onClick={() => navigate("/savings")}>💾 Savings</li>
        <li onClick={() => navigate("/settings")}>⚙️ Settings</li>
      </ul>
      <Button variant="danger" onClick={() => navigate("/")} className="logout-button">
        Logout
      </Button>
    </div>
  );
}

export default Sidebar;
