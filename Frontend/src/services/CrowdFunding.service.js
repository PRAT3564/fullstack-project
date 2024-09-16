import axios from "axios";

const BACKEND_URL = "http://localhost:8081/crowdfunding/api";

const axiosInstance = axios.create({
    baseURL: BACKEND_URL,
});

const crowdfundingService = {
    getAllProjects: async () => {
        try {
            const res = await axiosInstance.get("/projects");
            return res.data;
        } catch (error) {
            throw error;
        }
    },

    getProjectById: async (projectId) => {
        try {
            const res = await axiosInstance.get(`/projects/${projectId}`);
            return res.data;
        } catch (error) {
            throw error;
        }
    },

    createProject: async (projectData) => {
        try {
            const res = await axiosInstance.post("/projects", projectData);
            return res.data;
        } catch (error) {
            throw error;
        }
    },

    updateProject: async (projectId, projectData) => {
        try {
            const res = await axiosInstance.put(`/projects/${projectId}`, projectData);
            return res.data;
        } catch (error) {
            throw error;
        }
    },

    deleteProject: async (projectId) => {
        try {
            const res = await axiosInstance.delete(`/projects/${projectId}`);
            return res.data;
        } catch (error) {
            throw error;
        }
    },

    getAllInvestmentsForProject: async (projectId) => {
        try {
            const res = await axiosInstance.get(`/investments/project/${projectId}`);
            return res.data;
        } catch (error) {
            throw error;
        }
    },

    getInvestmentById: async (investmentId) => {
        try {
            const res = await axiosInstance.get(`/investments/${investmentId}`);
            return res.data;
        } catch (error) {
            throw error;
        }
    },

    createInvestment: async (investmentData) => {
        try {
            const res = await axiosInstance.post("/investments", investmentData);
            return res.data;
        } catch (error) {
            throw error;
        }
    },

    updateInvestment: async (investmentId, investmentData) => {
        try {
            const res = await axiosInstance.put(`/investments/${investmentId}`, investmentData);
            return res.data;
        } catch (error) {
            throw error;
        }
    },

    deleteInvestment: async (investmentId) => {
        try {
            const res = await axiosInstance.delete(`/investments/${investmentId}`);
            return res.data;
        } catch (error) {
            throw error;
        }
    },

    getInvestmentsByProjectId: async (projectId) => {
        try {
            const res = await axiosInstance.get(`/investments/project/${projectId}`);
            return res.data;
        } catch (error) {
            throw error;
        }
    },

    getInvestmentsByInvestorName: async (investorName) => {
        try {
            const res = await axiosInstance.get(`/investments/investor/${investorName}`);
            return res.data;
        } catch (error) {
            throw error;
        }
    },
};

export default crowdfundingService;
