import React from "react";
import { useState, useEffect } from "react";
import { Container, Row, Col, Card, Form, Alert } from "react-bootstrap";

interface ApiResponse {
    result: string;
}

export const NumbertoWordsConvComponent = () => {
    const [number, setNumber] = useState<string>('');
    const [words, setWords] = useState<string>('');
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        if (number) {
            const num = parseFloat(number);
            
            if (!isNaN(num)) {
                const fetchData = async () => {
                    try {
                        const response = await fetch(`http://localhost:8080/convert?number=${number}`);
                        const data: ApiResponse = await response.json();
                        
                        setWords(data.result);
                        setError(null);
                    } catch (error) {
                        setError('Error converting number');
                        setWords('');
                    }
                  };
                  
                  fetchData();
            } else {
                setWords('Please enter a valid number');
            }
        } else {
            setWords('');
        }
    }, [number]);

    

    return (
        <Container className="mt-5">
            <Row className="justify-content-md-center">
                <Col md="6">
                    <Card>
                        <Card.Header as="h5">Number to Words Converter</Card.Header>
                        <Card.Body>
                            <Form>
                                <Form.Group controlId="formNumber">
                                    <Form.Label>Enter a Number</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Enter number (e.g., 123.50)"
                                        value={number}
                                        onChange={(e) => setNumber(e.target.value)}
                                        maxLength={19}
                                    />
                                </Form.Group>
                            </Form>

                            {error && (
                                <Alert variant="danger" className="mt-3">
                                    {error}
                                </Alert>
                            )}
                            {words && (
                                <Alert variant="info" className="mt-3">
                                    {words}
                                </Alert>
                            )}
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
};
