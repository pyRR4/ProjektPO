const travels = [
  { lastName: "Smith", firstName: "John", email: "john@example.com", phone: "123456789", arrivalDate: "2023-01-15", departureDate: "2023-01-20", country: "Poland", city: "Warsaw", address: "Hotel 1", organizer: "TravelAgency A" },
  { lastName: "Doe", firstName: "Jane", email: "jane@example.com", phone: "987654321", arrivalDate: "2023-02-10", departureDate: "2023-02-15", country: "Germany", city: "Berlin", address: "Hotel 2", organizer: "TravelAgency B" },
  { lastName: "Kowalski", firstName: "Piotr", email: "piotr.k@example.com", phone: "111222333", arrivalDate: "2023-03-05", departureDate: "2023-03-12", country: "Poland", city: "Krakow", address: "Guesthouse Krakow", organizer: "TravelAgency C" },
  { lastName: "Johnson", firstName: "Emily", email: "emily.j@example.com", phone: "444555666", arrivalDate: "2023-04-01", departureDate: "2023-04-07", country: "USA", city: "New York", address: "Hilton NY", organizer: "TravelAgency D" },
  { lastName: "Garcia", firstName: "Carlos", email: "carlos.g@example.com", phone: "777888999", arrivalDate: "2023-05-10", departureDate: "2023-05-17", country: "Spain", city: "Barcelona", address: "Hotel Barca", organizer: "TravelAgency E" },
  { lastName: "Lefevre", firstName: "Marie", email: "marie.l@example.com", phone: "666555444", arrivalDate: "2023-06-15", departureDate: "2023-06-22", country: "France", city: "Paris", address: "Hotel Paris", organizer: "TravelAgency F" },
];

// Obsługa formularza filtrowania
document.getElementById("filter-form").addEventListener("submit", function (e) {
  e.preventDefault();

  const startDate = new Date(document.getElementById("start-date").value);
  const endDate = new Date(document.getElementById("end-date").value);

  if (endDate < startDate) {
    alert("Departure date must be after arrival date.");
    return;
  }

  const filteredTravels = travels.filter((travel) => {
    const arrival = new Date(travel.arrivalDate);
    return arrival >= startDate && arrival <= endDate;
  });

  displayReport(filteredTravels);
  populateCountryFilter(filteredTravels);
  document.getElementById("country-filter").style.display = "block";
});

// Przycisk "Wróć" - odświeżenie strony
document.getElementById("refresh-button").addEventListener("click", () => {
  location.reload();
});

// Wyświetlanie raportu
function displayReport(data) {
  const tbody = document.querySelector("#report-table tbody");
  tbody.innerHTML = "";
  data.forEach((travel) => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${travel.lastName}</td>
      <td>${travel.firstName}</td>
      <td>${travel.email}</td>
      <td>${travel.phone}</td>
      <td>${travel.arrivalDate}</td>
      <td>${travel.departureDate}</td>
      <td>${travel.country}</td>
      <td>${travel.city}</td>
      <td>${travel.address}</td>
      <td>${travel.organizer}</td>
    `;
    tbody.appendChild(row);
  });
}

// Uzupełnianie filtra kraju
function populateCountryFilter(data) {
  const countrySelect = document.getElementById("filter-country");
  countrySelect.innerHTML = '<option value="">All</option>';
  const countries = [...new Set(data.map((travel) => travel.country))];
  countries.forEach((country) => {
    const option = document.createElement("option");
    option.value = country;
    option.textContent = country;
    countrySelect.appendChild(option);
  });
}

// Filtrowanie według kraju
document.getElementById("filter-button").addEventListener("click", () => {
  const selectedCountry = document.getElementById("filter-country").value;

  const startDate = new Date(document.getElementById("start-date").value);
  const endDate = new Date(document.getElementById("end-date").value);

  const filteredTravels = travels.filter((travel) => {
    const arrival = new Date(travel.arrivalDate);
    return (
      arrival >= startDate &&
      arrival <= endDate &&
      (selectedCountry === "" || travel.country === selectedCountry)
    );
  });

  displayReport(filteredTravels);
});
