import React, { useState } from 'react';
import crowdfundingService from '../services/CrowdFunding.service';

const InvestmentList = ({ deleteInvestment, setSelectedInvestment }) => {
    const [investorName, setInvestorName] = useState('');
    const [searchedInvestments, setSearchedInvestments] = useState([]);

    const handleSearch = async () => {
        try {
            const investmentsData = await crowdfundingService.getInvestmentsByInvestorName(investorName);
            setSearchedInvestments(investmentsData);
        } catch (error) {
            console.error('Failed to fetch investments:', error);
            setSearchedInvestments([]);
        }
    };

    return (
        <div>
            <h3>Search Investments by Investor Name</h3>
            <input
                type="text"
                value={investorName}
                onChange={(e) => setInvestorName(e.target.value)}
                placeholder="Enter investor name"
            />
            <button onClick={handleSearch}>Search</button>

            {searchedInvestments.length > 0 && (
                <div>
                    <h4>Investments List</h4>
                    {searchedInvestments.map((investment) => (
                        <div key={investment.id}>
                            <span><strong>Amount:</strong> {investment.amount}</span>
                            <span style={{ marginLeft: "15px" }}><strong>Investor Name:</strong> {investment.investorName}</span>
                            <span style={{ marginLeft: "15px" }}><strong>Project ID:</strong> {investment.projectId}</span>
                            <button onClick={() => setSelectedInvestment(investment)} style={{ marginLeft: "15px" }}>Edit</button>
                            <button onClick={() => deleteInvestment(investment.id)} style={{ marginLeft: "10px" }}>Delete</button>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
};

export default InvestmentList;
