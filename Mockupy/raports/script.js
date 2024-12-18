// Mock data for demonstration with more countries
const travels = [
  { date: "2023-01-15", country: "Poland" },
  { date: "2023-01-25", country: "Germany" },
  { date: "2023-02-10", country: "France" },
  { date: "2023-02-15", country: "Poland" },
  { date: "2023-03-05", country: "USA" },
  { date: "2023-03-18", country: "Spain" },
  { date: "2023-04-01", country: "Italy" },
  { date: "2023-04-15", country: "Germany" },
  { date: "2023-05-05", country: "France" },
  { date: "2023-06-12", country: "Poland" },
  { date: "2023-06-25", country: "Spain" },
  { date: "2023-07-01", country: "Italy" },
  { date: "2023-07-15", country: "USA" },
  { date: "2023-08-05", country: "Poland" },
  { date: "2023-08-20", country: "France" },
  { date: "2023-09-01", country: "Germany" },
  { date: "2023-09-15", country: "Spain" },
  { date: "2023-10-10", country: "USA" },
  { date: "2023-10-20", country: "Italy" },
  { date: "2023-11-05", country: "France" },
  { date: "2023-11-15", country: "Germany" },
  { date: "2023-12-01", country: "Spain" },
  { date: "2023-12-20", country: "Poland" },
];

// Lista krajów do wyboru
const countries = ["Poland", "Germany", "France", "USA", "Spain", "Italy"];

// Dodanie krajów do selektora w formularzu
const countrySelect = document.getElementById("country");
countries.forEach((country) => {
  const option = document.createElement("option");
  option.value = country;
  option.textContent = country;
  countrySelect.appendChild(option);
});

// Obsługa formularza filtrowania
document.getElementById("filter-form").addEventListener("submit", function (e) {
  e.preventDefault();

  // Pobranie wartości z formularza
  const startDate = new Date(document.getElementById("start-date").value);
  const endDate = new Date(document.getElementById("end-date").value);
  const country = document.getElementById("country").value;

  if (endDate < startDate) {
    alert("End date must be after start date.");
    return;
  }

  // Filtrowanie podróży według interwału i kraju
  const filteredTravels = travels.filter((travel) => {
    const travelDate = new Date(travel.date);
    return (
      travelDate >= startDate &&
      travelDate <= endDate &&
      (country === "" || travel.country === country)
    );
  });

  // Generowanie statystyk
  const stats = {};
  filteredTravels.forEach((travel) => {
    const [year, month] = travel.date.split("-");
    const key = `${month}-${year}`;
    stats[key] = (stats[key] || 0) + 1;
  });

  // Wyświetlanie wyników w tabeli
  const tbody = document.querySelector("#report-table tbody");
  tbody.innerHTML = ""; // Czyszczenie poprzednich wyników
  Object.entries(stats).forEach(([key, count]) => {
    const [month, year] = key.split("-");
    const row = document.createElement("tr");
    row.innerHTML = `
        <td>${month}</td>
        <td>${year}</td>
        <td>${count}</td>
      `;
    tbody.appendChild(row);
  });
  document.getElementById("download-button").disabled = false;
});

document
  .getElementById("download-button")
  .addEventListener("click", function () {
    const table = document.getElementById("report-table");
    const rows = table.getElementsByTagName("tr");

    let csvContent = "Month,Year,Number of Travels\n";

    for (let i = 1; i < rows.length; i++) {
      const cells = rows[i].getElementsByTagName("td");
      const rowData = [
        cells[0].textContent,
        cells[1].textContent,
        cells[2].textContent,
      ];
      csvContent += rowData.join(",") + "\n";
    }

    const blob = new Blob([csvContent], { type: "text/csv;charset=utf-8;" });
    const link = document.createElement("a");
    const url = URL.createObjectURL(blob);

    link.setAttribute("href", url);
    link.setAttribute("download", "travel_statistics.csv");
    link.style.visibility = "hidden";

    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  });

document.getElementById("download-button").disabled = true;
