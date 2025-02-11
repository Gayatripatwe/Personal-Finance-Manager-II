// import React, { useState } from "react";
// import Sidebar from "../Pages/Components/SideBar";
// import Button from "react-bootstrap/Button";
// import Modal from "react-bootstrap/Modal";
// import Form from "react-bootstrap/Form";
// import { FaCalendarAlt } from "react-icons/fa";
// import "./Income.css";
// import MonthPicker from "../Pages/Components/Calender";

// function Income() {
//   const [showModal, setShowModal] = useState(false);
//   const [incomeSources, setIncomeSources] = useState([]);
//   const [newIncome, setNewIncome] = useState("");
//   const [amount, setAmount] = useState("");
//   const [selectedMonth, setSelectedMonth] = useState(new Date().getMonth());
//   const [selectedYear, setSelectedYear] = useState(new Date().getFullYear());
//   const [showCalendar, setShowCalendar] = useState(false);

//   const totalIncome = incomeSources.reduce((sum, source) => sum + source.amount, 0);

//   const handleAddIncome = () => {
//     if (newIncome.trim() !== "" && amount > 0) {
//       setIncomeSources([...incomeSources, { name: newIncome, amount: parseFloat(amount) }]);
//       setNewIncome("");
//       setAmount("");
//       setShowModal(false);
//     }
//   };

//   const handleMonthChange = ({ month, year }) => {
//     setSelectedMonth(month);
//     setSelectedYear(year);
//     setShowCalendar(false);
//   };

//   return (
//     <div className="income-container">
//       <Sidebar />

//       <div className="income-controls">
//         <div className="income-header">
//           <Button variant="outline-primary" className="calendar-button" onClick={() => setShowCalendar(true)}>
//             <FaCalendarAlt className="calendar-icon" /> Select Month
//           </Button>
//           {showCalendar && (
//             <div className="calendar-dropdown-content">
//               <MonthPicker onSelect={handleMonthChange} />
//             </div>
//           )}
//         </div>

//         <div className="total-income-box">
//           <h4>Total Income</h4>
//           <p>${totalIncome.toFixed(2)}</p>
//         </div>

//         <Button variant="primary" onClick={() => setShowModal(true)} className="add-income-button">
//           + Add Income
//         </Button>
//       </div>

//       <div className="income-main">
//         <div className="assigned-income">
//           <h3>Income for {selectedMonth + 1}/{selectedYear}</h3>
//           <div className="income-list" style={{ maxHeight: "300px", overflowY: "auto" }}>
//             <ul>
//               {incomeSources.map((source, index) => (
//                 <li key={index}>
//                   {source.name}: <strong>${source.amount.toFixed(2)}</strong>
//                 </li>
//               ))}
//             </ul>
//           </div>
//         </div>
//       </div>

//       <Modal show={showModal} onHide={() => setShowModal(false)} centered>
//         <Modal.Header closeButton>
//           <Modal.Title>Add Income</Modal.Title>
//         </Modal.Header>
//         <Modal.Body>
//           <Form>
//             <Form.Group>
//               <Form.Label>Income Source</Form.Label>
//               <Form.Control type="text" placeholder="Enter income source" value={newIncome} onChange={(e) => setNewIncome(e.target.value)} />
//             </Form.Group>
//             <Form.Group>
//               <Form.Label>Amount</Form.Label>
//               <Form.Control type="number" placeholder="Enter amount" value={amount} onChange={(e) => setAmount(e.target.value)} />
//             </Form.Group>
//           </Form>
//         </Modal.Body>
//         <Modal.Footer>
//           <Button variant="secondary" onClick={() => setShowModal(false)}>Close</Button>
//           <Button variant="primary" onClick={handleAddIncome}>Add</Button>
//         </Modal.Footer>
//       </Modal>
//     </div>
//   );
// }

// export default Income;
import React, { useState } from "react";
import Sidebar from "../Pages/Components/SideBar";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import { FaCalendarAlt } from "react-icons/fa";
import "./Income.css";
import MonthPicker from "../Pages/Components/Calender";

function Income() {
  const [showModal, setShowModal] = useState(false);
  const [incomeSources, setIncomeSources] = useState([]);
  const [newIncome, setNewIncome] = useState("");
  const [amount, setAmount] = useState("");
  const [selectedMonth, setSelectedMonth] = useState(new Date().getMonth());
  const [selectedYear, setSelectedYear] = useState(new Date().getFullYear());
  const [showCalendar, setShowCalendar] = useState(false);
  const [incomeDate, setIncomeDate] = useState("");

  const totalIncome = incomeSources.reduce((sum, source) => sum + source.amount, 0);

  const handleAddIncome = () => {
    if (newIncome.trim() !== "" && amount > 0 && incomeDate) {
      setIncomeSources([
        ...incomeSources,
        { name: newIncome, amount: parseFloat(amount), date: incomeDate },
      ]);
      setNewIncome("");
      setAmount("");
      setIncomeDate("");
      setShowModal(false);
    }
  };

  const handleMonthChange = ({ month, year }) => {
    setSelectedMonth(month);
    setSelectedYear(year);
    setShowCalendar(false);
  };

  return (
    <div className="income-container">
      <Sidebar />

      <div className="income-controls">
        <div className="income-header">
          <Button
            variant="outline-primary"
            className="calendar-button"
            onClick={() => setShowCalendar(true)}
          >
            <FaCalendarAlt className="calendar-icon" /> Select Month
          </Button>
          {showCalendar && (
            <div className="calendar-dropdown-content">
              <MonthPicker onSelect={handleMonthChange} />
            </div>
          )}
        </div>

        <div className="total-income-box">
          <h4>Total Income</h4>
          <p>${totalIncome.toFixed(2)}</p>
        </div>

        <Button
          variant="primary"
          onClick={() => setShowModal(true)}
          className="add-income-button"
        >
          + Add Income
        </Button>
      </div>

      <div className="income-main">
        <div className="assigned-income">
          <h3>Income for {selectedMonth + 1}/{selectedYear}</h3>
          <div className="income-list">
            <ul>
              {incomeSources.map((source, index) => (
                <li key={index}>
                  {source.name}: <strong>${source.amount.toFixed(2)}</strong> 
                  <span className="income-date"> (Date: {source.date})</span>
                </li>
              ))}
            </ul>
          </div>
        </div>
      </div>

      <Modal show={showModal} onHide={() => setShowModal(false)} centered>
        <Modal.Header closeButton>
          <Modal.Title>Add Income</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group>
              <Form.Label>Income Source</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter income source"
                value={newIncome}
                onChange={(e) => setNewIncome(e.target.value)}
              />
            </Form.Group>
            <Form.Group>
              <Form.Label>Amount</Form.Label>
              <Form.Control
                type="number"
                placeholder="Enter amount"
                value={amount}
                onChange={(e) => setAmount(e.target.value)}
              />
            </Form.Group>
            <Form.Group>
              <Form.Label>Date</Form.Label>
              <Form.Control
                type="date"
                value={incomeDate}
                onChange={(e) => setIncomeDate(e.target.value)}
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowModal(false)}>
            Close
          </Button>
          <Button variant="primary" onClick={handleAddIncome}>
            Add
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}

export default Income;
