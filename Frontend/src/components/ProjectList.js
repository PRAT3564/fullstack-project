import React, { useState } from 'react';
import crowdfundingService from '../services/CrowdFunding.service';

const ProjectList = ({ projects, deleteProject, setSelectedProject }) => {
    const [investments, setInvestments] = useState({});

    const fetchInvestmentsForProject = async (projectId) => {
        try {
            const investmentsData = await crowdfundingService.getAllInvestmentsForProject(projectId);
            setInvestments((prevInvestments) => ({
                ...prevInvestments,
                [projectId]: investmentsData
            }));
        } catch (error) {
            console.error('Failed to fetch investments for project:', error);
        }
    };

    return (
        <div>
            <h3>Project List</h3>
            {projects.length > 0 ? (
                projects.map((project) => (
                    <div key={project.id} style={{ marginBottom: "20px", paddingBottom: "10px", borderBottom: "1px solid #ccc" }}>
                        <div><strong>Name:</strong> {project.name}</div>
                        <div><strong>Description:</strong> {project.description}</div>
                        <div><strong>Goal Amount:</strong> {project.goalAmount}</div>
                        <div><strong>Amount Raised:</strong> {project.amountRaised}</div>
                        <div>
                            <button onClick={() => setSelectedProject(project)} style={{ marginRight: "10px" }}>Edit</button>
                            <button onClick={() => deleteProject(project.id)} style={{ marginRight: "10px" }}>Delete</button>
                            <button onClick={() => fetchInvestmentsForProject(project.id)}>Get all investments</button>
                        </div>
                        {investments[project.id] && (
                            <div>
                                <h4>Investments for {project.name}</h4>
                                {investments[project.id].map((investment) => (
                                    <div key={investment.id}>
                                        <p>Amount: {investment.amount}</p>
                                        <p>Investor Name: {investment.investorName}</p>
                                    </div>
                                ))}
                            </div>
                        )}
                    </div>
                ))
            ) : (
                <p>No projects found</p>
            )}
        </div>
    );
};

export default ProjectList;



// import React from 'react';

// const ProjectList = ({ projects, deleteProject, setSelectedProject }) => {
//     return (
//         <div>
//             <h3>Project List</h3>
//             {projects.length > 0 ? (
//                 projects.map((project) => (
//                     <div key={project.id} style={{ marginBottom: "20px", paddingBottom: "10px", borderBottom: "1px solid #ccc" }}>
//                         <span><strong>Name:</strong> {project.name}</span>
//                         <span style={{ marginLeft: "15px" }}><strong>Description:</strong> {project.description}</span>
//                         <span style={{ marginLeft: "15px" }}><strong>Goal Amount:</strong> {project.goalAmount}</span>
//                         <span style={{ marginLeft: "15px" }}><strong>Amount Raised:</strong> {project.amountRaised}</span>
//                         <span style={{ marginLeft: "15px" }}>
//                             <button onClick={() => setSelectedProject(project)} style={{ marginRight: "10px" }}>Edit</button>
//                             <button onClick={() => deleteProject(project.id)}>Delete</button>
//                         </span>
//                     </div>
//                 ))
//             ) : (
//                 <p>No projects found</p>
//             )}
//         </div>
//     );
// };

// export default ProjectList;
