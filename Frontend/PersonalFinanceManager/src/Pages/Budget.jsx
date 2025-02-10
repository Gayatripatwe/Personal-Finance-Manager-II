// import React, { useState } from "react";
// import Sidebar from "../Pages/Components/SideBar";
// import Button from "react-bootstrap/Button";
// import Modal from "react-bootstrap/Modal";
// import Form from "react-bootstrap/Form";
// import "./Budget.css";

// function Budget() {
//   const [showModal, setShowModal] = useState(false);
//   const [needs, setNeeds] = useState(["🏠 Rent", "🛒 Groceries"]);
//   const [wants, setWants] = useState(["🎉 Entertainment", "🛍️ Shopping"]);
//   const [newItem, setNewItem] = useState("");
//   const [category, setCategory] = useState("needs");

//   const handleAddItem = () => {
//     if (newItem.trim() !== "") {
//       category === "needs" ? setNeeds([...needs, newItem]) : setWants([...wants, newItem]);
//       setNewItem("");
//       setShowModal(false);
//     }
//   };

//   return (
//     <div className="budget-container">
//       <Sidebar />

//       <div className="budget-main">
//         {/* Filters Section */}
//         <div className="filters">
//           <Button variant="outline-dark">Unfunded</Button>
//           <Button variant="outline-dark">Overfunded</Button>
//           <Button variant="outline-dark">Fully Funded</Button>
//         </div>

//         {/* Left Side - Assign Button */}
//         <div className="left-section">
//           <Button variant="primary" onClick={() => setShowModal(true)}>Assign</Button>
//         </div>

//         {/* Right Side - Assigned This Month */}
//         <div className="right-section">
//           <h3>Assigned This Month</h3>
//           <div className="assigned-list">
//             <div className="section">
//               <h4>✅ Needs</h4>
//               <ul>{needs.map((item, index) => <li key={index}>{item}</li>)}</ul>
//             </div>
//             <div className="section">
//               <h4>💡 Wants</h4>
//               <ul>{wants.map((item, index) => <li key={index}>{item}</li>)}</ul>
//             </div>
//           </div>
//         </div>
//       </div>

//       {/* Modal for Assigning Needs & Wants */}
//       <Modal show={showModal} onHide={() => setShowModal(false)} centered>
//         <Modal.Header closeButton>
//           <Modal.Title>Add Item</Modal.Title>
//         </Modal.Header>
//         <Modal.Body>
//           <Form>
//             <Form.Group>
//               <Form.Label>Item Name</Form.Label>
//               <Form.Control type="text" placeholder="Enter item name" value={newItem} onChange={(e) => setNewItem(e.target.value)} />
//             </Form.Group>
//             <Form.Group>
//               <Form.Label>Category</Form.Label>
//               <Form.Select value={category} onChange={(e) => setCategory(e.target.value)}>
//                 <option value="needs">Needs</option>
//                 <option value="wants">Wants</option>
//               </Form.Select>
//             </Form.Group>
//           </Form>
//         </Modal.Body>
//         <Modal.Footer>
//           <Button variant="secondary" onClick={() => setShowModal(false)}>Close</Button>
//           <Button variant="primary" onClick={handleAddItem}>Add</Button>
//         </Modal.Footer>
//       </Modal>
//     </div>
//   );
// }

// export default Budget;


import React, { useState } from "react";
import Sidebar from "../Pages/Components/SideBar";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import { FaCalendarAlt } from "react-icons/fa"; // Import calendar icon
import "./Budget.css";
import MonthPicker from "../Pages/Components/Calender"

function Budget() {
  const [showModal, setShowModal] = useState(false);
  const [needs, setNeeds] = useState(["🏠 Rent", "🛒 Groceries"]);
  const [wants, setWants] = useState(["🎉 Entertainment", "🛍️ Shopping"]);
  const [newItem, setNewItem] = useState("");
  const [category, setCategory] = useState("needs");
  const [totalBudget, setTotalBudget] = useState(0); // Example total budget
  const [selectedMonth, setSelectedMonth] = useState(new Date().getMonth());
  const [selectedYear, setSelectedYear] = useState(new Date().getFullYear());// State for selected month
  const [showCalendar, setShowCalendar] = useState(false); 

  const handleAddItem = () => {
    if (newItem.trim() !== "") {
      category === "needs" ? setNeeds([...needs, newItem]) : setWants([...wants, newItem]);
      setNewItem("");
      setShowModal(false);
    }
  };

  const handleMonthChange = ({ month, year }) => {
    setSelectedMonth(month);
    setSelectedYear(year);
    setShowCalendar(false); // Close the calendar modal after selection
  };

  return (
    <div className="budget-container">
      <Sidebar />

      {/* Budget Controls */}
      <div className="budget-controls">
        {/* Calendar Icon for Selecting Month */}
        <div className="budget-header">
          {/* Calendar for selecting month */}
          <Button variant="outline-primary" className="calendar-button" onClick={() => setShowCalendar(true)}>
          <FaCalendarAlt className="calendar-icon" /> Select Month
        </Button>
        {showCalendar && (
            <div className="calendar-dropdown-content">
              <MonthPicker onSelect={handleMonthChange} />
            </div>
          )}
        </div>
        {/* Total Budget Display */}
        <div className="total-budget-box">
          <h4>Total Budget</h4>
          <p>${totalBudget}</p>
        </div>

        {/* Add Budget Button */}
        <Button variant="primary" onClick={() => setShowModal(true)} className="add-budget-button">
         + Add Budget
        </Button>
      </div>

      {/* Assigned Budget List */}
      <div className="budget-main">
        <div className="assigned-budget">
          {/* <h3>Assigned This Month</h3> */}
          <h3>Budget for {selectedMonth + 1}/{selectedYear}</h3>
          <div className="assigned-list">
            <div className="section">
              <h4>✅ Needs</h4>
              <ul>{needs.map((item, index) => <li key={index}>{item}</li>)}</ul>
            </div>
            <div className="section">
              <h4>💡 Wants</h4>
              <ul>{wants.map((item, index) => <li key={index}>{item}</li>)}</ul>
            </div>
          </div>
        </div>
      </div>



      {/* Modal for Adding Budget */}
      <Modal show={showModal} onHide={() => setShowModal(false)} centered>
        <Modal.Header closeButton>
          <Modal.Title>Add Budget</Modal.Title>
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

export default Budget;
