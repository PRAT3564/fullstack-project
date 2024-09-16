import React, { useState, useEffect } from 'react';
import ProjectForm from './components/ProjectForm';
import ProjectList from './components/ProjectList';
import InvestmentForm from './components/InvestmentForm';
import InvestmentList from './components/InvestmentList';
import crowdfundingService from './services/CrowdFunding.service';

function App() {
  const [projects, setProjects] = useState([]);
  const [selectedProject, setSelectedProject] = useState(null);
  const [investments, setInvestments] = useState([]);
  const [selectedInvestment, setSelectedInvestment] = useState(null);

  useEffect(() => {
    fetchProjects();
    fetchInvestments();
  }, []);

  async function fetchProjects() {
    try {
      const projectsData = await crowdfundingService.getAllProjects();
      setProjects(projectsData);
    } catch (error) {
      console.error('Failed to fetch projects:', error);
    }
  }

  async function fetchInvestments() {
    try {
      const investmentsData = await crowdfundingService.getAllInvestments();
      setInvestments(investmentsData);
    } catch (error) {
      console.error('Failed to fetch investments:', error);
    }
  }

  const createProject = async (projectData) => {
    try {
      const newProject = await crowdfundingService.createProject(projectData);
      setProjects([...projects, newProject]);
    } catch (error) {
      console.error('Failed to create project:', error);
    }
  };

  const updateProject = async (projectId, projectData) => {
    try {
      const updatedProject = await crowdfundingService.updateProject(projectId, projectData);
      setProjects(projects.map((project) => (project.id === projectId ? updatedProject : project)));
      setSelectedProject(null);
    } catch (error) {
      console.error('Failed to update project:', error);
    }
  };

  const deleteProject = async (projectId) => {
    try {
      await crowdfundingService.deleteProject(projectId);
      setProjects(projects.filter((project) => project.id !== projectId));
    } catch (error) {
      console.error('Failed to delete project:', error);
    }
  };

  const createInvestment = async (investmentData) => {
    try {
      const newInvestment = await crowdfundingService.createInvestment(investmentData);
      setInvestments([...investments, newInvestment]);
    } catch (error) {
      console.error('Failed to create investment:', error);
    }
  };

  const updateInvestment = async (investmentId, investmentData) => {
    try {
      const updatedInvestment = await crowdfundingService.updateInvestment(investmentId, investmentData);
      setInvestments(investments.map((investment) => (investment.id === investmentId ? updatedInvestment : investment)));
      setSelectedInvestment(null);
    } catch (error) {
      console.error('Failed to update investment:', error);
    }
  };

  const deleteInvestment = async (investmentId) => {
    try {
      await crowdfundingService.deleteInvestment(investmentId);
      setInvestments(investments.filter((investment) => investment.id !== investmentId));
    } catch (error) {
      console.error('Failed to delete investment:', error);
    }
  };

  return (
    <div className="App">
      <h1>Crowdfunding Dashboard</h1>
      <ProjectForm
        createProject={createProject}
        updateProject={updateProject}
        selectedProject={selectedProject}
        setSelectedProject={setSelectedProject}
      />
      <ProjectList
        projects={projects}
        deleteProject={deleteProject}
        setSelectedProject={setSelectedProject}
      />
      <InvestmentForm
        projects={projects}
        createInvestment={createInvestment}
        updateInvestment={updateInvestment}
        selectedInvestment={selectedInvestment}
        setSelectedInvestment={setSelectedInvestment}
      />
      <InvestmentList
        investments={investments}
        deleteInvestment={deleteInvestment}
        setSelectedInvestment={setSelectedInvestment}
      />
    </div>
  );
}

export default App;