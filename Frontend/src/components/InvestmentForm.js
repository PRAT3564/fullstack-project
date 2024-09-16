import React, { useState, useEffect } from 'react';

const InvestmentForm = ({
    createInvestment,
    updateInvestment,
    selectedInvestment,
    setSelectedInvestment,
    projects // Add this prop to receive the list of projects
}) => {
    const [investment, setInvestment] = useState({ id: '', amount: '', investorName: '', projectId: '' });

    useEffect(() => {
        if (selectedInvestment) {
            setInvestment({ ...selectedInvestment });
        } else {
            setInvestment({ id: '', amount: '', investorName: '', projectId: '' });
        }
    }, [selectedInvestment]);

    const isEditForm = !!selectedInvestment;

    const handleSubmit = (e) => {
        e.preventDefault();
        if (isEditForm) {
            updateInvestment(investment.id, investment);
        } else {
            createInvestment(investment);
        }
        setInvestment({ id: '', amount: '', investorName: '', projectId: '' });
        setSelectedInvestment(null);
    };

    return (
        <div>
            <h2>{isEditForm ? 'Edit Investment' : 'Create Investment'}</h2>
            <form onSubmit={handleSubmit}>
                <label htmlFor="amount">Amount:</label>
                <input
                    id="amount"
                    type="number"
                    value={investment.amount}
                    onChange={(e) => setInvestment({ ...investment, amount: e.target.value })}
                    placeholder="Investment Amount"
                    required
                />
                <label htmlFor="investorName">Investor Name:</label>
                <input
                    id="investorName"
                    type="text"
                    value={investment.investorName}
                    onChange={(e) => setInvestment({ ...investment, investorName: e.target.value })}
                    placeholder="Investor Name"
                    required
                />
                <label htmlFor="projectId">Project:</label>
                <select
                    id="projectId"
                    value={investment.projectId}
                    onChange={(e) => setInvestment({ ...investment, projectId: e.target.value })}
                    required
                >
                    <option value="">Select a project</option>
                    {projects.map((project) => (
                        <option key={project.id} value={project.id}>
                            {project.name}
                        </option>
                    ))}
                </select>
                <button type="submit">{isEditForm ? 'Update Investment' : 'Create Investment'}</button>
            </form>
        </div>
    );
};

export default InvestmentForm;



// import React, { useState, useEffect } from 'react';

// const InvestmentForm = ({ createInvestment, updateInvestment, selectedInvestment, setSelectedInvestment }) => {
//     const [investment, setInvestment] = useState({ id: '', amount: '', investorName: '', projectId: '' });

//     useEffect(() => {
//         if (selectedInvestment) {
//             setInvestment({ ...selectedInvestment });
//         } else {
//             setInvestment({ id: '', amount: '', investorName: '', projectId: '' });
//         }
//     }, [selectedInvestment]);

//     const isEditForm = !!selectedInvestment;

//     const handleSubmit = (e) => {
//         e.preventDefault();
//         if (isEditForm) {
//             updateInvestment(investment.id, investment);
//         } else {
//             createInvestment(investment);
//         }
//         setInvestment({ id: '', amount: '', investorName: '', projectId: '' });
//         setSelectedInvestment(null); // Reset selected investment after update
//     };

//     return (
//         <div>
//             <h2>{isEditForm ? 'Edit Investment' : 'Create Investment'}</h2>
//             <form onSubmit={handleSubmit}>
//                 <label htmlFor="amount">Amount:</label>
//                 <input
//                     id="amount"
//                     type="number"
//                     value={investment.amount}
//                     onChange={(e) => setInvestment({ ...investment, amount: e.target.value })}
//                     placeholder="Investment Amount"
//                     required
//                 />
//                 <label htmlFor="investorName">Investor Name:</label>
//                 <input
//                     id="investorName"
//                     type="text"
//                     value={investment.investorName}
//                     onChange={(e) => setInvestment({ ...investment, investorName: e.target.value })}
//                     placeholder="Investor Name"
//                     required
//                 />
//                 <label htmlFor="projectId">Project ID:</label>
//                 <input
//                     id="projectId"
//                     type="number"
//                     value={investment.projectId}
//                     onChange={(e) => setInvestment({ ...investment, projectId: e.target.value })}
//                     placeholder="Associated Project ID"
//                     required
//                 />
//                 <button type="submit">{isEditForm ? 'Update Investment' : 'Create Investment'}</button>
//             </form>
//         </div>
//     );
// };

// export default InvestmentForm;
