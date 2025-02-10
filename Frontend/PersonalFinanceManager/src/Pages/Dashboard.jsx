import React, { useState } from "react";
import Sidebar from "../Pages/Components/SideBar";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import "./Dashboard.css";

function Dashboard() {
  const [showModal, setShowModal] = useState(false);
  const [needs, setNeeds] = useState(["🏠 Rent", "🛒 Groceries"]);
  const [wants, setWants] = useState(["🎉 Entertainment", "🛍️ Shopping"]);
  const [newItem, setNewItem] = useState("");
  const [category, setCategory] = useState("needs");

  const handleAddItem = () => {
    if (newItem.trim() !== "") {
      category === "needs" ? setNeeds([...needs, newItem]) : setWants([...wants, newItem]);
      setNewItem("");
      setShowModal(false);
    }
  };

  return (
    <div className="dashboard-container">
      <Sidebar />

      {/* Main Content */}
      <div className="main-content">
        <h1>Needs & Wants</h1>
        <div className="needs-wants">
          <div className="section">
            <h3>✅ Needs</h3>
            <ul>{needs.map((item, index) => <li key={index}>{item}</li>)}</ul>
          </div>
          <div className="section">
            <h3>💡 Wants</h3>
            <ul>{wants.map((item, index) => <li key={index}>{item}</li>)}</ul>
          </div>
        </div>
      </div>

      <button className="pulse-button" onClick={() => setShowModal(true)}>+</button>

      <Modal show={showModal} onHide={() => setShowModal(false)} centered>
        <Modal.Header closeButton>
          <Modal.Title>Add Item</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group>
              <Form.Label>Item Name</Form.Label>
              <Form.Control type="text" placeholder="Enter item name" value={newItem} onChange={(e) => setNewItem(e.target.value)} />
            </Form.Group>
            <Form.Group>
              <Form.Label>Category</Form.Label>
              <Form.Select value={category} onChange={(e) => setCategory(e.target.value)}>
                <option value="needs">Needs</option>
                <option value="wants">Wants</option>
              </Form.Select>
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowModal(false)}>Close</Button>
          <Button variant="primary" onClick={handleAddItem}>Add</Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}

export default Dashboard;
