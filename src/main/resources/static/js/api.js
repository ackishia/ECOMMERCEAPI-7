async function apiFetch(url, options = {}) {
    try {
        const response = await fetch(url, {
            ...options,
            headers: {
                "Content-Type": "application/json",
                ...options.headers
            }
        });

        // Not logged in
        if (response.status === 401) {
            window.location.href = "/login.html";
            return null;
        }

        // Logged in but wrong role
        if (response.status === 403) {
            document.body.innerHTML = `
                <h1>Access Denied</h1>
                <p>You do not have permission to access this page.</p>
            `;
            return null;
        }

        return response;

    } catch (error) {
        console.error("API Error:", error);
    }
}