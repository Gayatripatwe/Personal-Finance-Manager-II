import React, { useState } from "react";
import Sidebar from "../Pages/Components/SideBar";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Table from "react-bootstrap/Table";
import { FaCalendarAlt } from "react-icons/fa"; 
import "./Budget.css";
import MonthPicker from "../Pages/Components/Calender";
import PaginationComponent from "../Pages/Components/PaginantionComponent"; 

function Budget() {
  const [showModal, setShowModal] = useState(false);
  const [needs, setNeeds] = useState([]);
  const [wants, setWants] = useState([]);
  const [newItem, setNewItem] = useState("");
  const [category, setCategory] = useState("needs");
  const [totalBudget, setTotalBudget] = useState(0); 
  const [selectedMonth, setSelectedMonth] = useState(new Date().getMonth());
  const [selectedYear, setSelectedYear] = useState(new Date().getFullYear());
  const [showCalendar, setShowCalendar] = useState(false); 
  const [amount, setAmount] = useState("");

  const itemsPerPage = 3; 

  // const handleAddItem = () => {
  //   if (newItem.trim() !== "") {
  //     if (category === "needs") {
  //       setNeeds((prevNeeds) => [...prevNeeds, { name: newItem, type: "Needs" }]);
  //     } else {
  //       setWants((prevWants) => [...prevWants, { name: newItem, type: "Wants" }]);
  //     }
  //     setNewItem("");
  //     setShowModal(false);
  //   }
  // };

  const handleAddItem = () => {
    if (newItem.trim() !== "") {
      const newBudgetItem = { name: newItem, amount: amount, type: category };
      
      if (category === "needs") {
        setNeeds((prevNeeds) => [...prevNeeds, newBudgetItem]);
      } else {
        setWants((prevWants) => [...prevWants, newBudgetItem]);
      }
      
      setNewItem("");
      setAmount("");
      setShowModal(false);
    }
  };
  

  const handleMonthChange = ({ month, year }) => {
    setSelectedMonth(month);
    setSelectedYear(year);
    setShowCalendar(false); 
  };

  const allItems = [...needs, ...wants];

  return (
    <div className="budget-container">
      <Sidebar />
<div className="budget-controls">
  {/* Left Side - Total Budget */}
  <div className="total-budget-box">
    <h4>Total Assigned</h4>
    <p>${totalBudget}</p>
  </div>

  {/* Right Side - Select Month & Assign Budget */}
  <div className="budget-header">
    <Button variant="outline-primary" className="calendar-button" onClick={() => setShowCalendar(true)}>
      <FaCalendarAlt className="calendar-icon" /> Select Month
    </Button>

    {showCalendar && (
      <div className="calendar-dropdown-content">
        <MonthPicker onSelect={handleMonthChange} />
      </div>
    )}

    <Button variant="primary" onClick={() => setShowModal(true)} className="add-budget-button">
      + Assign Budget
    </Button>
  </div>
</div>

<div className="budget-main">
  <div className="assigned-budget">
    <h3>Budget for {selectedMonth + 1}/{selectedYear}</h3>

    {/* Needs Table */}
    <div className="table-container">
      <h4>✅ Needs</h4>
      <PaginationComponent
        items={needs}
        itemsPerPage={itemsPerPage}
        renderItems={(paginatedNeeds) => (
          <Table striped bordered hover className="budget-table">
            <thead>
              <tr>
                <th>#</th>
                <th>Item Name</th>
                <th>Amount</th>
                <th>Edit</th>
                <th>Delete</th>
              </tr>
            </thead>
            <tbody>
              {paginatedNeeds.map((item, index) => (
                <tr key={index}>
                  <td>{index + 1}</td>
                  <td>{item.name}</td>
                  <td>₹{item.amount}</td>
                  <td><Button variant="warning" size="sm">Edit</Button></td>
                  <td><Button variant="danger" size="sm">Delete</Button></td>
                </tr>
              ))}
            </tbody>
          </Table>
        )}
      />
    </div>

    {/* Wants Table */}
    <div className="table-container">
      <h4>💡 Wants</h4>
      <PaginationComponent
        items={wants}
        itemsPerPage={itemsPerPage}
        renderItems={(paginatedWants) => (
          <Table striped bordered hover className="budget-table">
            <thead>
              <tr>
                <th>#</th>
                <th>Item Name</th>
                <th>Amount</th>
                <th>Edit</th>
                <th>Delete</th>
              </tr>
            </thead>
            <tbody>
              {paginatedWants.map((item, index) => (
                <tr key={index}>
                  <td>{index + 1}</td>
                  <td>{item.name}</td>
                  <td>₹{item.amount}</td>
                  <td><Button variant="warning" size="sm">Edit</Button></td>
                  <td><Button variant="danger" size="sm">Delete</Button></td>
                </tr>
              ))}
            </tbody>
          </Table>
        )}
      />
    </div>
  </div>
</div>




<Modal show={showModal} onHide={() => setShowModal(false)} centered className="custom-modal">
  <Modal.Header>
    <Modal.Title>Assign Budget</Modal.Title>
  </Modal.Header>
  <Modal.Body>
    <Form>
      <Form.Group>
        <Form.Label>Item Name</Form.Label>
        <Form.Control type="text" placeholder="Enter item name" value={newItem} onChange={(e) => setNewItem(e.target.value)} />
      </Form.Group>
      <Form.Group>
        <Form.Label>Amount</Form.Label>
        <Form.Control type="text" placeholder="Enter amount" value={amount} onChange={(e) => setAmount(e.target.value)} />
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
    <Button variant="primary" onClick={handleAddItem}>Assign</Button>
  </Modal.Footer>
</Modal>
    </div>
  );
}

export default Budget;
