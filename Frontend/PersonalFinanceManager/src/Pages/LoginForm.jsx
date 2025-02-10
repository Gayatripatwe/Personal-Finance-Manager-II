import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";

function LoginForm({ closeModal }) {
  const [showSignup, setShowSignup] = useState(false);
  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();
    closeModal(); 
    navigate("/dashboard"); 
  };

  return (
    <Form onSubmit={handleLogin}>
      <Form.Group className="mb-3" controlId="formBasicEmail">
        <Form.Label>Email address</Form.Label>
        <Form.Control type="email" placeholder="Enter email" />
        <Form.Text className="text-muted">
          We'll never share your email with anyone else.
        </Form.Text>
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicPassword">
        <Form.Label>Password</Form.Label>
        <Form.Control type="password" placeholder="Password" />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicCheckbox">
        <Form.Check type="checkbox" label="Check me out" />
      </Form.Group>

      {/* ✅ Submit Button (Same as `BasicExample`) */}
      <Button variant="primary" type="submit" className="w-100">
        Submit
      </Button>

      {/* ✅ OR Separator (Same as `BasicExample`) */}
      <div className="text-center my-3">— OR —</div>

      {/* ✅ Login with Google (Additional Feature) */}
      <Button variant="danger" className="w-100 mb-2">
        Login with Google
      </Button>

      {/* ✅ Signup Link (For New Users) */}
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

      {/* ✅ Signup Modal */}
      <Modal show={showSignup} onHide={() => setShowSignup(false)} centered>
        <Modal.Header closeButton>
          <Modal.Title>Sign Up</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <SignupForm closeSignup={() => setShowSignup(false)} />
        </Modal.Body>
      </Modal>
    </Form>
  );
}

function SignupForm({ closeSignup }) {
  const handleSignup = (e) => {
    e.preventDefault();
    closeSignup(); // Close Signup Modal
  };

  return (
    <Form onSubmit={handleSignup}>
      <Form.Group className="mb-3" controlId="signupName">
        <Form.Label>Name</Form.Label>
        <Form.Control type="text" placeholder="Enter your name" />
      </Form.Group>

      <Form.Group className="mb-3" controlId="signupEmail">
        <Form.Label>Email address</Form.Label>
        <Form.Control type="email" placeholder="Enter email" />
      </Form.Group>

      <Form.Group className="mb-3" controlId="signupPassword">
        <Form.Label>Password</Form.Label>
        <Form.Control type="password" placeholder="Create password" />
      </Form.Group>

      <Form.Group className="mb-3" controlId="confirmPassword">
        <Form.Label>Confirm Password</Form.Label>
        <Form.Control type="password" placeholder="Confirm password" />
      </Form.Group>

      {/* ✅ Signup Button (Same Style as `BasicExample`) */}
      <Button variant="success" type="submit" className="w-100">
        Sign Up
      </Button>
    </Form>
  );
}

export default LoginForm;
