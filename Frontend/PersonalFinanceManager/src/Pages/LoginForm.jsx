import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import SignupForm from "./SignupForm"; // Import SignupForm
import './LoginForm.css';

function LoginForm({ closeModal }) {
  const [showSignup, setShowSignup] = useState(false); // Show signup form or not
  const [email, setEmail] = useState(""); // Email state
  const [password, setPassword] = useState(""); // Password state
  const [error, setError] = useState(""); // Error message state
  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();

    // Simple validation check
    if (!email || !password) {
      setError("Email and password are required.");
      return;
    }

    setError(""); // Clear previous errors

    // Close modal if provided
    if (closeModal) {
      closeModal();
    }

    navigate("/dashboard"); // Redirect to dashboard after successful login
  };

  return (
    <>
      <Form className="jp" onSubmit={handleLogin}>
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Email address</Form.Label>
          <Form.Control
            type="email"
            placeholder="Enter email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <Form.Text className="text-muted"><br></br>
            We'll never share your email with anyone else.
          </Form.Text>
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Password</Form.Label><br></br>
          <Form.Control
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </Form.Group>

        {/* Display error message if any */}
        {error && <p className="text-danger">{error}</p>}

        <Button variant="primary" type="submit" className="w-100">
          Login
        </Button>

        <div className="text-center my-3">— OR —</div>

        <Button variant="danger" className="w-100 mb-2">
          Login with Google
        </Button>

        <div className="text-center">
          Don't have an account?{" "}
          <span
            className="text-primary"
            style={{ cursor: "pointer", textDecoration: "underline" }}
            onClick={() => setShowSignup(true)} 
          >
            Sign up
          </span>
        </div>
      </Form>

      {/* Signup Modal */}
      <Modal show={showSignup} onHide={() => setShowSignup(false)} centered>
        <Modal.Header closeButton>
          <Modal.Title>Sign Up</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <SignupForm closeSignup={() => setShowSignup(false)} />
        </Modal.Body>
      </Modal>
    </>
  );
}

export default LoginForm;
