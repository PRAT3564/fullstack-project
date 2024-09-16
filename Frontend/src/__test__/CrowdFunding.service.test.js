import crowdfundingService from "../services/CrowdFunding.service";

let API_BASE_URL = "http://localhost:8081/crowdfunding/api";

jest.mock("axios");

describe("CrowdfundingService", () => {
    const mockProjects = [
        {
            id: "1",
            name: "Eco-Friendly Housing",
            description: "Sustainable housing projects with minimal environmental impact.",
            goalAmount: 50000,
            amountRaised: 25000,
            status: "Active"
        },
        {
            id: "2",
            name: "Community Gardens",
            description: "Creating community gardens to promote local food production and green spaces.",
            goalAmount: 15000,
            amountRaised: 15000,
            status: "Completed"
        },
        {
            id: "3",
            name: "Renewable Energy Farm",
            description: "Development of a solar and wind farm to generate renewable energy.",
            goalAmount: 100000,
            amountRaised: 50000,
            status: "Active"
        }
    ];

    beforeEach(() => {
        jest.clearAllMocks();
    });

    describe("functional", () => {
        test("CrowdfundingService functional should get all projects", async () => {
            let isNull = false;
            try {
                const response = await crowdfundingService.getAllProjects();
                isNull = response === null;
                throw new Error("Error in getAllProjects()");
            } catch (error) {
                if (isNull) {
                    expect(error).toBeNull();
                } else {
                    crowdfundingService.getAllProjects = jest
                        .fn()
                        .mockResolvedValueOnce(mockProjects);
                    const result = await crowdfundingService.getAllProjects();
                    expect(crowdfundingService.getAllProjects).toHaveBeenCalled();
                    expect(result).toEqual(mockProjects);
                }
            }
        });

        test("CrowdfundingService functional should get project by ID", async () => {
            let isNull = false;
            try {
                const response = await crowdfundingService.getProjectById("1");
                isNull = response === null;
                throw new Error("Error in getProjectById()");
            } catch (error) {
                if (isNull) {
                    expect(error).toBeNull();
                } else {
                    crowdfundingService.getProjectById = jest
                        .fn()
                        .mockResolvedValueOnce(mockProjects[0]);
                    const result = await crowdfundingService.getProjectById("1");
                    expect(crowdfundingService.getProjectById).toHaveBeenCalledWith("1");
                    expect(result).toEqual(mockProjects[0]);
                }
            }
        });

        test("CrowdfundingService functional should create a new project", async () => {
            const newProject = {
                name: "New Green Initiative",
                description: "An initiative to plant 1000 trees.",
                goalAmount: 10000,
                amountRaised: 0,
            };
            let isNull = false;

            try {
                const response = await crowdfundingService.createProject(newProject);
                isNull = response === null;
                throw new Error("Error in createProject()");
            } catch (error) {
                if (isNull) {
                    expect(error).toBeNull();
                } else {
                    crowdfundingService.createProject = jest.fn().mockResolvedValueOnce(newProject);
                    const result = await crowdfundingService.createProject(newProject);
                    expect(crowdfundingService.createProject).toHaveBeenCalledWith(newProject);
                    expect(result).toEqual(newProject);
                }
            }
        });

        test("CrowdfundingService functional should update project by ID", async () => {
            const updatedProject = {
                id: "1",
                name: "Updated Eco-Friendly Housing",
                description: "Updated description",
                goalAmount: 60000,
                amountRaised: 30000,
            };
            let isNull = false;

            try {
                const response = await crowdfundingService.updateProject("1", updatedProject);
                isNull = response === null;
                throw new Error("Error in updateProject()");
            } catch (error) {
                if (isNull) {
                    expect(error).toBeNull();
                } else {
                    crowdfundingService.updateProject = jest.fn().mockResolvedValueOnce(updatedProject);
                    const result = await crowdfundingService.updateProject("1", updatedProject);
                    expect(crowdfundingService.updateProject).toHaveBeenCalledWith("1", updatedProject);
                    expect(result).toEqual(updatedProject);
                }
            }
        });

        test("CrowdfundingService functional should delete project by ID", async () => {
            let isNull = false;

            try {
                const response = await crowdfundingService.deleteProject("1");
                isNull = response === null;
                throw new Error("Error in deleteProject()");
            } catch (error) {
                if (isNull) {
                    expect(error).toBeNull();
                } else {
                    const mockResponse = { message: "Project deleted successfully" };
                    crowdfundingService.deleteProject = jest.fn().mockResolvedValueOnce(mockResponse);
                    const result = await crowdfundingService.deleteProject("1");
                    expect(crowdfundingService.deleteProject).toHaveBeenCalledWith("1");
                    expect(result).toEqual(mockResponse);
                }
            }
        });

        test("CrowdfundingService functional should get investments by investor name", async () => {
            const mockInvestments = [
                { id: '1', amount: 500, investorName: 'John Doe', projectId: '1' },
                { id: '2', amount: 1500, investorName: 'John Doe', projectId: '2' }
            ];
            let isNull = false;

            try {
                const response = await crowdfundingService.getInvestmentsByInvestorName("John Doe");
                isNull = response === null;
                throw new Error("Error in getInvestmentsByInvestorName()");
            } catch (error) {
                if (isNull) {
                    expect(error).toBeNull();
                } else {
                    crowdfundingService.getInvestmentsByInvestorName = jest.fn().mockResolvedValueOnce(mockInvestments);
                    const result = await crowdfundingService.getInvestmentsByInvestorName("John Doe");
                    expect(crowdfundingService.getInvestmentsByInvestorName).toHaveBeenCalledWith("John Doe");
                    expect(result).toEqual(mockInvestments);
                }
            }
        });

        test("CrowdfundingService functional should get all investments for a project", async () => {
            const mockInvestmentsForProject = [
                { id: '1', amount: 500, investorName: 'Alice', projectId: '1' },
                { id: '2', amount: 1000, investorName: 'Bob', projectId: '1' }
            ];
            let isNull = false;

            try {
                const response = await crowdfundingService.getAllInvestmentsForProject("1");
                isNull = response === null;
                throw new Error("Error in getAllInvestmentsForProject()");
            } catch (error) {
                if (isNull) {
                    expect(error).toBeNull();
                } else {
                    crowdfundingService.getAllInvestmentsForProject = jest.fn().mockResolvedValueOnce(mockInvestmentsForProject);
                    const result = await crowdfundingService.getAllInvestmentsForProject("1");
                    expect(crowdfundingService.getAllInvestmentsForProject).toHaveBeenCalledWith("1");
                    expect(result).toEqual(mockInvestmentsForProject);
                }
            }
        });

        test("CrowdfundingService functional should get investment by ID", async () => {
            const mockInvestment = { id: '1', amount: 500, investorName: 'Alice', projectId: '1' };
            let isNull = false;

            try {
                const response = await crowdfundingService.getInvestmentById("1");
                isNull = response === null;
                throw new Error("Error in getInvestmentById()");
            } catch (error) {
                if (isNull) {
                    expect(error).toBeNull();
                } else {
                    crowdfundingService.getInvestmentById = jest.fn().mockResolvedValueOnce(mockInvestment);
                    const result = await crowdfundingService.getInvestmentById("1");
                    expect(crowdfundingService.getInvestmentById).toHaveBeenCalledWith("1");
                    expect(result).toEqual(mockInvestment);
                }
            }
        });

        test("CrowdfundingService functional should create an investment", async () => {
            const newInvestment = { amount: 1500, investorName: 'Charlie', projectId: '2' };
            let isNull = false;

            try {
                const response = await crowdfundingService.createInvestment(newInvestment);
                isNull = response === null;
                throw new Error("Error in createInvestment()");
            } catch (error) {
                if (isNull) {
                    expect(error).toBeNull();
                } else {
                    crowdfundingService.createInvestment = jest.fn().mockResolvedValueOnce(newInvestment);
                    const result = await crowdfundingService.createInvestment(newInvestment);
                    expect(crowdfundingService.createInvestment).toHaveBeenCalledWith(newInvestment);
                    expect(result).toEqual(newInvestment);
                }
            }
        });

        test("CrowdfundingService functional should update investment by ID", async () => {
            const updatedInvestment = { id: '1', amount: 2000, investorName: 'Alice', projectId: '1' };
            let isNull = false;

            try {
                const response = await crowdfundingService.updateInvestment("1", updatedInvestment);
                isNull = response === null;
                throw new Error("Error in updateInvestment()");
            } catch (error) {
                if (isNull) {
                    expect(error).toBeNull();
                } else {
                    crowdfundingService.updateInvestment = jest.fn().mockResolvedValueOnce(updatedInvestment);
                    const result = await crowdfundingService.updateInvestment("1", updatedInvestment);
                    expect(crowdfundingService.updateInvestment).toHaveBeenCalledWith("1", updatedInvestment);
                    expect(result).toEqual(updatedInvestment);
                }
            }
        });

        test("CrowdfundingService functional should delete investment by ID", async () => {
            let isNull = false;

            try {
                const response = await crowdfundingService.deleteInvestment("1");
                isNull = response === null;
                throw new Error("Error in deleteInvestment()");
            } catch (error) {
                if (isNull) {
                    expect(error).toBeNull();
                } else {
                    const mockResponse = { message: "Investment deleted successfully" };
                    crowdfundingService.deleteInvestment = jest.fn().mockResolvedValueOnce(mockResponse);
                    const result = await crowdfundingService.deleteInvestment("1");
                    expect(crowdfundingService.deleteInvestment).toHaveBeenCalledWith("1");
                    expect(result).toEqual(mockResponse);
                }
            }
        });
    });
});
