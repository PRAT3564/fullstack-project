import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import ProjectForm from '../components/ProjectForm';

describe('boundary', () => {
    const mockCreateProject = jest.fn();
    const mockUpdateProject = jest.fn();
    const mockSetSelectedProject = jest.fn();

    const baseProps = {
        createProject: mockCreateProject,
        updateProject: mockUpdateProject,
        setSelectedProject: mockSetSelectedProject,
    };

    test('ProjectForm boundary has "Create Project" button by default', () => {
        render(<ProjectForm {...baseProps} />);
        const createButton = screen.getByRole('button', { name: /create project/i });
        expect(createButton).toBeInTheDocument();
    });

    test('ProjectForm boundary has "Project Name" input', () => {
        render(<ProjectForm {...baseProps} />);
        const nameInput = screen.getByPlaceholderText('Project Name');
        expect(nameInput).toBeInTheDocument();
    });

    test('ProjectForm boundary has "Project Description" textarea', () => {
        render(<ProjectForm {...baseProps} />);
        const descriptionTextarea = screen.getByPlaceholderText('Project Description');
        expect(descriptionTextarea).toBeInTheDocument();
    });

    test('ProjectForm boundary has "Goal Amount" input', () => {
        render(<ProjectForm {...baseProps} />);
        const goalAmountInput = screen.getByPlaceholderText('Goal Amount');
        expect(goalAmountInput).toBeInTheDocument();
    });

    test('ProjectForm boundary has "Amount Raised" input', () => {
        render(<ProjectForm {...baseProps} />);
        const amountRaisedInput = screen.getByPlaceholderText('Amount Raised');
        expect(amountRaisedInput).toBeInTheDocument();
    });

    test('ProjectForm boundary updates to "Edit Project" mode', () => {
        const selectedProject = {
            id: '1',
            name: 'Existing Project',
            description: 'A description',
            goalAmount: '5000',
            amountRaised: '3000'
        };

        render(<ProjectForm {...baseProps} selectedProject={selectedProject} />);
        const updateButton = screen.getByRole('button', { name: /update project/i });
        expect(updateButton).toBeInTheDocument();

        const nameInput = screen.getByDisplayValue(selectedProject.name);
        expect(nameInput).toBeInTheDocument();

        const descriptionTextarea = screen.getByDisplayValue(selectedProject.description);
        expect(descriptionTextarea).toBeInTheDocument();
    });
});
