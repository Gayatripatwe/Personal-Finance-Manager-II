import React from 'react';
import Container from 'react-bootstrap/Container';
import Modal from 'react-bootstrap/Modal';
import { FaFacebook, FaInstagram } from 'react-icons/fa';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './LandingPage.css'; 

import CustomNavbar from './Components/NavBar';  // Import Navbar Component
import LoginForm from '../Pages/LoginForm';  // Import Login Component

function LandingPage() {
  const [showModal, setShowModal] = useState(false);
  const navigate = useNavigate();

  return (
    <>
      <CustomNavbar onLoginClick={() => setShowModal(true)} />

      {/* Main Content with Image on Right */}
      <div className="container main-container">
        <div className="text-content">
          <h1>How Will You Spend Your <span className="money-text">MONEY</span>, Life?</h1>
          <h3>Create your own plan and manage with Vaulta</h3>
        </div>
        <div className="image-content">
          <img src="public\LadingImage.jpg" alt="Vaulta Finance" className="landing-image" />
        </div>
      </div>

      {/* Login Modal */}
      <Modal show={showModal} onHide={() => setShowModal(false)} centered>
        <Modal.Header closeButton>
          <Modal.Title>Login</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <LoginForm closeModal={() => setShowModal(false)} />
        </Modal.Body>
      </Modal>

      {/*Footer Section */}
      <Footer />
    </>
  );
}

function Footer() {
  return (
    <footer className="footer bg-dark text-light text-center py-3 mt-5">
      <p>Follow us on:</p>
      <div>
        <a href="https://facebook.com" target="_blank" rel="noopener noreferrer" className="text-light mx-2">
          <FaFacebook size={24} />
        </a>
        <a href="https://instagram.com" target="_blank" rel="noopener noreferrer" className="text-light mx-2">
          <FaInstagram size={24} />
        </a>
      </div>
    </footer>
  );
}

export default LandingPage;
