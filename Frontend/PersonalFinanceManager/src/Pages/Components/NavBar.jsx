import React from 'react';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import Button from 'react-bootstrap/Button';

function CustomNavbar({ onLoginClick }) {
  return (
    // <Navbar expand="lg" className="bg-dark navbar-dark w-100 fixed-top py-3">
    //   <Container>
    //     {/* ✅ Left-Aligned Nav Links */}
    //     <Navbar.Brand href="#home" className="text-light fw-bold">
    //       Vaulta
    //     </Navbar.Brand>
    //     <Navbar.Toggle aria-controls="basic-navbar-nav" />
    //     <Navbar.Collapse id="basic-navbar-nav">
    //       <Nav className="me-auto">  
    //         <Nav.Link href="#home" className="text-light">What is Vaulta?</Nav.Link>
    //         <Nav.Link href="#pricing" className="text-light">Pricing</Nav.Link>
    //         <Nav.Link href="#learn" className="text-light">Learn</Nav.Link>
    //         <NavDropdown title={<span className="text-light">More</span>} id="basic-nav-dropdown">
    //           <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
    //           <NavDropdown.Item href="#action/3.2">Another action</NavDropdown.Item>
    //           <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
    //           <NavDropdown.Divider />
    //           <NavDropdown.Item href="#action/3.4">Separated link</NavDropdown.Item>
    //         </NavDropdown>
    //       </Nav>

    //       {/* ✅ Right-Aligned Login & Try Free Buttons */}
    //       <div className="d-flex">
    //         <Button variant="outline-light" className="me-2" onClick={onLoginClick}>Login</Button>
    //         <Button variant="blue">Try For Free</Button>
    //       </div>
    //     </Navbar.Collapse>
    //   </Container>
    // </Navbar>


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

        {/* Buttons aligned to the right */}
        <div className="d-flex ms-auto">
          <Button variant="outline-light" className="me-2">Login</Button>
          <Button variant="primary">Sign Up</Button>
        </div>
      </Navbar.Collapse>
    </Container>
  </Navbar>
  );
}

export default CustomNavbar;



// import React from 'react';
// import Container from 'react-bootstrap/Container';
// import Nav from 'react-bootstrap/Nav';
// import Navbar from 'react-bootstrap/Navbar';
// import NavDropdown from 'react-bootstrap/NavDropdown';
// import Button from 'react-bootstrap/Button';

// function CustomNavbar({ onLoginClick }) {
//   return (
//     <Navbar expand="lg" className="bg-dark navbar-dark w-100 fixed-top py-3">
//       <Container>
//         <Navbar.Brand href="#home" className="text-light fw-bold">
//           Vaulta
//         </Navbar.Brand>
//         <Navbar.Toggle aria-controls="basic-navbar-nav" />
//         <Navbar.Collapse id="basic-navbar-nav">
//           {/* ✅ FIXED: Links now align horizontally like BasicExample */}
//           <Nav className="me-auto">  
//             <Nav.Link href="#home" className="text-light">What is Vaulta?</Nav.Link>
//             <Nav.Link href="#pricing" className="text-light">Pricing</Nav.Link>
//             <Nav.Link href="#learn" className="text-light">Learn</Nav.Link>
//             <NavDropdown title={<span className="text-light">More</span>} id="basic-nav-dropdown">
//               <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
//               <NavDropdown.Item href="#action/3.2">Another action</NavDropdown.Item>
//               <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
//               <NavDropdown.Divider />
//               <NavDropdown.Item href="#action/3.4">Separated link</NavDropdown.Item>
//             </NavDropdown>
//           </Nav>

//           {/* Buttons on Right */}
//           <div className="d-flex">
//             <Button variant="outline-light" className="me-2" onClick={onLoginClick}>Login</Button>
//             <Button variant="primary">Try For Month</Button>
//           </div>
//         </Navbar.Collapse>
//       </Container>
//     </Navbar>
//   );
// }

// export default CustomNavbar;
