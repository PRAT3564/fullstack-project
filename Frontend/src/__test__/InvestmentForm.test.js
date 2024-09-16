import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import InvestmentForm from '../components/InvestmentForm';

const mockProjects = [
    { id: '1', name: 'Eco-Friendly Housing' },
    { id: '2', name: 'Community Gardens' }
];

describe('boundary', () => {
    // Providing mock functions for props
    const mockCreateInvestment = jest.fn();
    const mockUpdateInvestment = jest.fn();
    const mockSetSelectedInvestment = jest.fn();

    const baseProps = {
        createInvestment: mockCreateInvestment,
        updateInvestment: mockUpdateInvestment,
        setSelectedInvestment: mockSetSelectedInvestment,
        projects: mockProjects,
    };

    test('InvestmentForm boundary has "Create Investment" button', () => {
        render(<InvestmentForm {...baseProps} />);
        const createButton = screen.getByRole('button', { name: /create investment/i });
        expect(createButton).toBeInTheDocument();
    });

    test('InvestmentForm boundary has "Amount" input', () => {
        render(<InvestmentForm {...baseProps} />);
        const amountInput = screen.getByPlaceholderText('Investment Amount');
        expect(amountInput).toBeInTheDocument();
    });

    test('InvestmentForm boundary has "Investor Name" input', () => {
        render(<InvestmentForm {...baseProps} />);
        const investorNameInput = screen.getByPlaceholderText('Investor Name');
        expect(investorNameInput).toBeInTheDocument();
    });

    test('InvestmentForm boundary has "Project" select dropdown', () => {
        render(<InvestmentForm {...baseProps} />);
        const projectSelect = screen.getByLabelText('Project:');
        expect(projectSelect).toBeInTheDocument();
        expect(projectSelect).toHaveValue('');
    });

    test('InvestmentForm boundary "Project" select includes options for projects', () => {
        render(<InvestmentForm {...baseProps} />);
        const projectOptions = screen.getAllByRole('option');
        expect(projectOptions.length).toBeGreaterThan(1); // More than just the default "Select a project" option
        expect(projectOptions[1]).toHaveTextContent(mockProjects[0].name);
        expect(projectOptions[2]).toHaveTextContent(mockProjects[1].name);
    });

});
