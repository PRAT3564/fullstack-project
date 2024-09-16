import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom';
import InvestmentList from '../components/InvestmentList';
import crowdfundingService from '../services/CrowdFunding.service';

jest.mock('../services/CrowdFunding.service');

describe('boundary', () => {
    const mockDeleteInvestment = jest.fn();
    const mockSetSelectedInvestment = jest.fn();

    beforeEach(() => {
        jest.clearAllMocks();
    });

    test('InvestmentList boundary renders search input and button', () => {
        render(
            <InvestmentList
                deleteInvestment={mockDeleteInvestment}
                setSelectedInvestment={mockSetSelectedInvestment}
            />
        );
        expect(screen.getByPlaceholderText('Enter investor name')).toBeInTheDocument();
        expect(screen.getByRole('button', { name: /search/i })).toBeInTheDocument();
    });

    test('InvestmentList boundary displays investments after search', async () => {
        const mockInvestments = [
            { id: '1', amount: 100, investorName: 'John Doe', projectId: '1' },
            { id: '2', amount: 200, investorName: 'John Doe', projectId: '2' }
        ];
        crowdfundingService.getInvestmentsByInvestorName.mockResolvedValue(mockInvestments);
        render(
            <InvestmentList
                deleteInvestment={mockDeleteInvestment}
                setSelectedInvestment={mockSetSelectedInvestment}
            />
        );
        fireEvent.change(screen.getByPlaceholderText('Enter investor name'), { target: { value: 'John Doe' } });
        fireEvent.click(screen.getByRole('button', { name: /search/i }));
        const amountText = await screen.findAllByText(/Amount:/i); // Use regex to be more flexible
        expect(crowdfundingService.getInvestmentsByInvestorName).toHaveBeenCalledWith('John Doe');
        expect(amountText.length).toBeGreaterThan(0); // Ensure that at least one element with "Amount" is found
    });
});
