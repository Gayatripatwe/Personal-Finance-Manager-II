// import React, { useState } from "react";
// import { useNavigate } from "react-router-dom";
// import Button from "react-bootstrap/Button";
// import Modal from "react-bootstrap/Modal";
// import Form from "react-bootstrap/Form";
// import "./Dashboard.css"; // Ensure this CSS file is included

// function Dashboard() {
//   const navigate = useNavigate();
//   const [showModal, setShowModal] = useState(false);
//   const [needs, setNeeds] = useState(["🏠 Rent / Mortgage", "🛒 Groceries", "🚗 Transportation", "⚕️ Medical Expenses", "💡 Utility Bills"]);
//   const [wants, setWants] = useState(["🎉 Entertainment", "🛍️ Shopping", "🌍 Travel", "🍽️ Dining Out", "🎮 Gaming"]);
//   const [newItem, setNewItem] = useState("");
//   const [category, setCategory] = useState("needs");

//   const handleLogout = () => {
//     navigate("/"); // Navigate back to the landing page
//   };

//   const handleAddItem = () => {
//     if (newItem.trim() !== "") {
//       if (category === "needs") {
//         setNeeds([...needs, newItem]);
//       } else {
//         setWants([...wants, newItem]);
//       }
//       setNewItem("");
//       setShowModal(false);
//     }
//   };

//   return (
//     <div className="dashboard-container">
//       {/* Sidebar */}
//       <div className="sidebar">
//         <h2>Your Budget</h2>
//         <ul>
//           <li>🏦 Budget</li>
//           <li>💰 Income</li>
//           <li>📉 Expenses</li>
//           <li>💾 Savings</li>
//           <li>⚙️ Settings</li>
//         </ul>
//         <Button variant="danger" onClick={handleLogout} className="logout-button">
//           Logout
//         </Button>
//       </div>

//       {/* Main Content - Full Width */}
//       <div className="main-content">
//         <h1>Needs & Wants</h1>
//         <div className="needs-wants">
//           <div className="section">
//             <h3>✅ Needs</h3>
//             <ul>
//               {needs.map((item, index) => (
//                 <li key={index}>{item}</li>
//               ))}
//             </ul>
//           </div>
//           <div className="section">
//             <h3>💡 Wants</h3>
//             <ul>
//               {wants.map((item, index) => (
//                 <li key={index}>{item}</li>
//               ))}
//             </ul>
//           </div>
//         </div>
//       </div>

//       {/* Floating Pulse Button */}
//       <button className="pulse-button" onClick={() => setShowModal(true)}>+</button>

//       {/* Modal for Adding Items */}
// <Modal show={showModal} onHide={() => setShowModal(false)} centered className="custom-modal">
//   <Modal.Header closeButton className="modal-header">
//     <Modal.Title>Add New Item</Modal.Title>
//   </Modal.Header>
//   <Modal.Body className="modal-body">
//     <Form>
//       <Form.Group className="mb-3">
//         <Form.Label className="form-label">Item Name</Form.Label>
//         <Form.Control
//           type="text"
//           placeholder="Enter item name"
//           value={newItem}
//           onChange={(e) => setNewItem(e.target.value)}
//           className="form-input"
//         />
//       </Form.Group>
//       <Form.Group className="mb-3">
//         <Form.Label className="form-label">Category</Form.Label>
//         <Form.Select
//           value={category}
//           onChange={(e) => setCategory(e.target.value)}
//           className="form-select"
//         >
//           <option value="needs">Needs</option>
//           <option value="wants">Wants</option>
//         </Form.Select>
//       </Form.Group>
//     </Form>
//   </Modal.Body>
//   <Modal.Footer className="modal-footer">
//     <Button variant="secondary" onClick={() => setShowModal(false)} className="modal-button">
//       Close
//     </Button>
//     <Button variant="primary" onClick={handleAddItem} className="modal-button add-button">
//       Add Item
//     </Button>
//   </Modal.Footer>
// </Modal>

//     </div>
//   );
// }

// export default Dashboard;


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

      {/* Floating Add Button */}
      <button className="pulse-button" onClick={() => setShowModal(true)}>+</button>

      {/* Add Needs & Wants Modal */}
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
