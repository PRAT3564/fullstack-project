import React, { useState, useEffect } from 'react';

const ProjectForm = ({ createProject, updateProject, selectedProject, setSelectedProject }) => {
    const [project, setProject] = useState({ id: '', name: '', description: '', goalAmount: '', amountRaised: '' });

    useEffect(() => {
        if (selectedProject) {
            setProject({ ...selectedProject });
        } else {
            setProject({ id: '', name: '', description: '', goalAmount: '', amountRaised: '' });
        }
    }, [selectedProject]);

    const isEditForm = !!selectedProject;

    const handleSubmit = (e) => {
        e.preventDefault();
        if (isEditForm) {
            updateProject(project.id, project);
        } else {
            createProject(project);
        }
        setProject({ id: '', name: '', description: '', goalAmount: '', amountRaised: '' });
        setSelectedProject(null); // Reset selected project after update
    };

    return (
        <div>
            <h2>{isEditForm ? 'Edit Project' : 'Manage Projects'}</h2>
            <form onSubmit={handleSubmit}>
                <label htmlFor="projectName">Project Name:</label>
                <input
                    id="projectName"
                    type="text"
                    value={project.name}
                    onChange={(e) => setProject({ ...project, name: e.target.value })}
                    placeholder="Project Name"
                    required
                />
                <label htmlFor="projectDescription">Project Description:</label>
                <textarea
                    id="projectDescription"
                    value={project.description}
                    onChange={(e) => setProject({ ...project, description: e.target.value })}
                    placeholder="Project Description"
                    required
                />
                <label htmlFor="goalAmount">Goal Amount:</label>
                <input
                    id="goalAmount"
                    type="number"
                    value={project.goalAmount}
                    onChange={(e) => setProject({ ...project, goalAmount: e.target.value })}
                    placeholder="Goal Amount"
                    required
                />
                <label htmlFor="amountRaised">Amount Raised:</label>
                <input
                    id="amountRaised"
                    type="number"
                    value={project.amountRaised}
                    onChange={(e) => setProject({ ...project, amountRaised: e.target.value })}
                    placeholder="Amount Raised"
                    required
                />
                <button type="submit">{isEditForm ? 'Update Project' : 'Create Project'}</button>
            </form>
        </div>
    );
};

export default ProjectForm;
