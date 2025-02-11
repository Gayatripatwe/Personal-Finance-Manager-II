// import React from 'react';
// import Container from 'react-bootstrap/Container';
// import Nav from 'react-bootstrap/Nav';
// import Navbar from 'react-bootstrap/Navbar';
// import NavDropdown from 'react-bootstrap/NavDropdown';
// import Button from 'react-bootstrap/Button';

// function CustomNavbar({ onLoginClick }) {
//   return (

//     <Navbar expand="lg" className="bg-dark navbar-dark w-100" fixed="top" style={{ minHeight: '70px' }}>
//     <Container>
//       <Navbar.Brand href="#home">Vaulta</Navbar.Brand>
//       <Navbar.Toggle aria-controls="basic-navbar-nav" />
//       <Navbar.Collapse id="basic-navbar-nav">
//         <Nav className="me-auto">
//           <Nav.Link href="#home">What is Vaulta?</Nav.Link>
//           <Nav.Link href="#link">Pricing</Nav.Link>
//           <Nav.Link href="#link">Learn</Nav.Link>
//           <NavDropdown title="Dropdown" id="basic-nav-dropdown">
//             <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
//             <NavDropdown.Item href="#action/3.2">Another action</NavDropdown.Item>
//             <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
//             <NavDropdown.Divider />
//             <NavDropdown.Item href="#action/3.4">Separated link</NavDropdown.Item>
//           </NavDropdown>
//         </Nav>

//         {/* Buttons aligned to the right */}
//         <div className="d-flex ms-auto">
//           <Button variant="outline-light" className="me-2">Login</Button>
//           <Button variant="primary">Sign Up</Button>
//         </div>
//       </Navbar.Collapse>
//     </Container>
//   </Navbar>
//   );
// }

// export default CustomNavbar;



// import React from 'react';
// import Container from 'react-bootstrap/Container';
// import Nav from 'react-bootstrap/Nav';
// import Navbar from 'react-bootstrap/Navbar';
// import NavDropdown from 'react-bootstrap/NavDropdown';
// import Button from 'react-bootstrap/Button';
// import LoginForm from "./LoginForm";

// function CustomNavbar({ onLoginClick }) {
//   const [showLogin, setShowLogin] = useState(false);
//   return (


//     <Navbar expand="lg" className="bg-dark navbar-dark w-100" fixed="top" style={{ minHeight: '70px' }}>
//     <Container>
//       <Navbar.Brand href="#home">Vaulta</Navbar.Brand>
//       <Navbar.Toggle aria-controls="basic-navbar-nav" />
//       <Navbar.Collapse id="basic-navbar-nav">
//         <Nav className="me-auto">
//           <Nav.Link href="#home">What is Vaulta?</Nav.Link>
//           <Nav.Link href="#link">Pricing</Nav.Link>
//           <Nav.Link href="#link">Learn</Nav.Link>
//           <NavDropdown title="Dropdown" id="basic-nav-dropdown">
//             <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
//             <NavDropdown.Item href="#action/3.2">Another action</NavDropdown.Item>
//             <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
//             <NavDropdown.Divider />
//             <NavDropdown.Item href="#action/3.4">Separated link</NavDropdown.Item>
//           </NavDropdown>
//         </Nav>

       
//         <div className="d-flex ms-auto" style={{color:"red"}}>
//           {/* <Button variant="outline-light" className="me-2" onClick={onLoginClick}>Login</Button> */}
//           <Button variant="outline-light" className="me-2" onClick={() => setShowLogin(true)}>
//                 Login
//               </Button>

//         </div>
//       </Navbar.Collapse>
//     </Container>
//   </Navbar>
  
//   );
// }

// export default CustomNavbar;



import React, { useState } from 'react';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import Button from 'react-bootstrap/Button';
import Modal from "react-bootstrap/Modal";
import LoginForm from "../LoginForm"; // Import the login form

function CustomNavbar() {
  const [showLogin, setShowLogin] = useState(false);

  return (
    <>
      <Navbar expand="lg" className="bg-dark navbar-dark w-100" fixed="top" style={{ minHeight: '70px' }}>
        <Container>
          <Navbar.Brand href="#home">Vaulta</Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <Nav.Link href="#home">What is Vaulta?</Nav.Link>
              <Nav.Link href="#link">Pricing</Nav.Link>
              <Nav.Link href="#link">Learn</Nav.Link>
              <NavDropdown title="Dropdown" id="basic-nav-dropdown">
                <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
                <NavDropdown.Item href="#action/3.2">Another action</NavDropdown.Item>
                <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
                <NavDropdown.Divider />
                <NavDropdown.Item href="#action/3.4">Separated link</NavDropdown.Item>
              </NavDropdown>
            </Nav>

            <div className="d-flex ms-auto">
              <Button variant="outline-light" className="me-2" onClick={() => setShowLogin(true)}>
                Login
              </Button>
            </div>
          </Navbar.Collapse>
        </Container>
      </Navbar>

      {/* ✅ Login Modal */}
      <Modal show={showLogin} onHide={() => setShowLogin(false)} centered>
        <Modal.Header closeButton>
          <Modal.Title>Login</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <LoginForm closeModal={() => setShowLogin(false)} />
        </Modal.Body>
      </Modal>
    </>
  );
}

export default CustomNavbar;
