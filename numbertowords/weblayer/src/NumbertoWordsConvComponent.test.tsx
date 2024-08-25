import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import { NumbertoWordsConvComponent } from './component/NumbertoWordsConvComponent';

describe('NumberToWordsConverter', () => {
    test('renders the component', () => {
        render(<NumbertoWordsConvComponent />);
        expect(screen.getByText(/Number to Words Converter/i)).toBeInTheDocument();
    });

    test('converts number to words correctly', async () => {
        render(<NumbertoWordsConvComponent />);
        
        global.fetch = jest.fn(() =>
            Promise.resolve({
              json: () => Promise.resolve({ result: 'ONE HUNDRED AND TWENTY-THREE DOLLARS AND FORTY-FIVE CENTS' }),
            })
        ) as jest.Mock;
        
        const input = screen.getByPlaceholderText(/Enter number/i);
        fireEvent.change(input, { target: { value: '123.45' } });

        await waitFor(() => {
            const alertElement = screen.getByRole('alert');
            expect(alertElement).toHaveTextContent('ONE HUNDRED AND TWENTY-THREE DOLLARS AND FORTY-FIVE CENTS');
        });
    });

    test('converts a huge number to words correctly', async () => {
        render(<NumbertoWordsConvComponent />);
        
        global.fetch = jest.fn(() =>
            Promise.resolve({
              json: () => Promise.resolve({ result: 'ONE QUINTILLION AND TWO HUNDRED AND THIRTY-FOUR QUADRILLION AND FIVE HUNDRED AND SIXTY-SEVEN TRILLION AND EIGHT HUNDRED AND NINETY BILLION AND ONE HUNDRED AND TWENTY-THREE MILLION AND FOUR HUNDRED AND FIFTY-SIX THOUSAND AND SEVEN HUNDRED AND SIXTY-EIGHT DOLLARS' }),
            })
        ) as jest.Mock;
        
        const input = screen.getByPlaceholderText(/Enter number/i);
        fireEvent.change(input, { target: { value: '1234567890123456789' } });

        await waitFor(() => {
            const alertElement = screen.getByRole('alert');
            expect(alertElement).toHaveTextContent('ONE QUINTILLION AND TWO HUNDRED AND THIRTY-FOUR QUADRILLION AND FIVE HUNDRED AND SIXTY-SEVEN TRILLION AND EIGHT HUNDRED AND NINETY BILLION AND ONE HUNDRED AND TWENTY-THREE MILLION AND FOUR HUNDRED AND FIFTY-SIX THOUSAND AND SEVEN HUNDRED AND SIXTY-EIGHT DOLLARS');
        });
    });

    test('shows error for invalid input', () => {
        render(<NumbertoWordsConvComponent />);
        
        const input = screen.getByPlaceholderText(/Enter number/i);
        fireEvent.change(input, { target: { value: 'invalid' } });

        expect(screen.getByText(/Please enter a valid number/i)).toBeInTheDocument();
    });

});