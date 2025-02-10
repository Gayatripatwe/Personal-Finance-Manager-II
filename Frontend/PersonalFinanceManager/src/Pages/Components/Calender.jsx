import React, { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faChevronLeft, faChevronRight } from "@fortawesome/free-solid-svg-icons";
import "./Calender.css";

const MonthPicker = ({ onSelect }) => {
  const months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
  const [selectedMonth, setSelectedMonth] = useState(new Date().getMonth()); // Default: Current month
  const [selectedYear, setSelectedYear] = useState(new Date().getFullYear()); // Default: Current year

  const handleMonthSelect = (index) => {
    setSelectedMonth(index);
    onSelect && onSelect({ month: index, year: selectedYear }); // Pass selected month/year to parent
  };

  return (
    <div className="month-picker">
      {/* Year Selection */}
      <div className="year-selector">
        <FontAwesomeIcon icon={faChevronLeft} className="arrow" onClick={() => setSelectedYear(selectedYear - 1)} />
        <h3>{selectedYear}</h3>
        <FontAwesomeIcon icon={faChevronRight} className="arrow" onClick={() => setSelectedYear(selectedYear + 1)} />
      </div>

      {/* Months Grid */}
      <div className="months-grid">
        {months.map((month, index) => (
          <div
            key={index}
            className={`month ${selectedMonth === index ? "selected" : ""}`}
            onClick={() => handleMonthSelect(index)}
          >
            {month}
          </div>
        ))}
      </div>
    </div>
  );
};

export default MonthPicker;
