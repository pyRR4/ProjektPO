const apiBase = "http://localhost:8080/api";

/**
 * Fetches all countries from the backend.
 * @returns {Promise<Array>} List of countries
 */
async function fetchCountries() {
    const response = await fetch(`${apiBase}/countries`);
    if (!response.ok) throw new Error("Failed to fetch countries");
    return response.json();
}

/**
 * Fetches all countries from the backend.
 * @returns {Promise<Array>} List of countries
 */
async function fetchConsulates() {
    const response = await fetch(`${apiBase}/consulates`);
    if (!response.ok) throw new Error("Failed to fetch countries");
    return response.json();
}

/**
 * Fetches consulate details by ID.
 * @param {string} consulateId
 * @returns {Promise<Object>} Consulate details
 */
async function fetchConsulateById(consulateId) {
    const response = await fetch(`${apiBase}/consulates/${consulateId}`);
    if (!response.ok) throw new Error("Failed to fetch consulate");
    return response.json();
}

/**
 * Submits consulate data to the backend.
 * @param {string} consulateId
 * @param {Object} payload
 * @returns {Promise<void>}
 */
async function submitConsulateData(consulateId, payload) {
    const response = await fetch(`${apiBase}/consulates/${consulateId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload),
    });
    if (!response.ok) throw new Error("Failed to update consulate");
}

/**
 * Submits consulate data to the backend.
 * @param {Object} payload
 * @returns {Promise<void>}
 */
async function createConsulate(payload) {
    const response = await fetch(`${apiBase}/consulates`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload),
    });
    if (!response.ok) throw new Error("Failed to update consulate");
}

/**
 * Deletes a consulate by ID.
 * @param {string} consulateId - The ID of the consulate to delete.
 * @returns {Promise<void>}
 */
async function deleteConsulateById(consulateId) {
    const response = await fetch(`${apiBase}/consulates/${consulateId}`, {
        method: "DELETE"
    });
    if (!response.ok) throw new Error("Failed to delete the consulate");
}
