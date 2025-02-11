import React, { useState } from "react";
import Pagination from "react-bootstrap/Pagination";

const PaginationComponent = ({ items, itemsPerPage, renderItems }) => {
  const [currentPage, setCurrentPage] = useState(1);
  const totalPages = Math.ceil(items.length / itemsPerPage);

  // Slice the items for the current page
  const paginatedItems = items.slice((currentPage - 1) * itemsPerPage, currentPage * itemsPerPage);

  return (
    <div>
      {/* Render items */}
      {renderItems(paginatedItems)}

      {/* Show pagination only if there are more than itemsPerPage */}
      {items.length > itemsPerPage && (
        <Pagination>
          {currentPage > 1 && <Pagination.Prev onClick={() => setCurrentPage(currentPage - 1)} />}
          {[...Array(totalPages).keys()].map((number) => (
            <Pagination.Item key={number + 1} active={number + 1 === currentPage} onClick={() => setCurrentPage(number + 1)}>
              {number + 1}
            </Pagination.Item>
          ))}
          {currentPage < totalPages && <Pagination.Next onClick={() => setCurrentPage(currentPage + 1)} />}
        </Pagination>
      )}
    </div>
  );
};

export default PaginationComponent;
