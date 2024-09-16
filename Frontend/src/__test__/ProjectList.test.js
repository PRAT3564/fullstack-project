import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom';
import ProjectList from '../components/ProjectList';
import crowdfundingService from '../services/CrowdFunding.service';

jest.mock('../services/CrowdFunding.service');

describe('boundary', () => {
    const mockProjects = [
        { id: '1', name: 'Project 1', description: 'Description 1', goalAmount: 10000, amountRaised: 5000 },
        { id: '2', name: 'Project 2', description: 'Description 2', goalAmount: 20000, amountRaised: 15000 }
    ];
    const mockInvestments = [
        { id: '1', amount: 1000, investorName: 'Investor 1', projectId: '1' },
        { id: '2', amount: 4000, investorName: 'Investor 2', projectId: '1' }
    ];

    const mockDeleteProject = jest.fn();
    const mockSetSelectedProject = jest.fn();

    beforeEach(() => {
        jest.clearAllMocks();
        crowdfundingService.getAllInvestmentsForProject.mockResolvedValue(mockInvestments);
    });

    test('ProjectList boundary renders project list and fetches investments for a project', async () => {
        render(
            <ProjectList
                projects={mockProjects}
                deleteProject={mockDeleteProject}
                setSelectedProject={mockSetSelectedProject}
            />
        );
        expect(screen.getByText('Project 1')).toBeInTheDocument();
        expect(screen.getByText('Project 2')).toBeInTheDocument();
        const getAllInvestmentsButtons = screen.getAllByText('Get all investments');
        fireEvent.click(getAllInvestmentsButtons[0]);
        await screen.findByText(/Investments for Project 1/);
        expect(screen.getByText('Amount: 1000')).toBeInTheDocument();
        expect(screen.getByText('Investor Name: Investor 1')).toBeInTheDocument();
        expect(crowdfundingService.getAllInvestmentsForProject).toHaveBeenCalledWith('1');
    });
});
