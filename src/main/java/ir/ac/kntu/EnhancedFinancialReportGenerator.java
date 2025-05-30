package ir.ac.kntu;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

public class EnhancedFinancialReportGenerator {
    private final Seller seller;
    private final int currentYear = Year.now().getValue();
    private final Month currentMonth = LocalDateTime.now().getMonth();

    public EnhancedFinancialReportGenerator(Seller seller) {
        this.seller = seller;
    }

    public String generateEnhancedReport() {
        try {
            if (seller == null) {
                return generateErrorHtml("فروشنده معتبر نیست (null)");
            }

            if (seller.getWallet() == null || seller.getOrders() == null) {
                return generateErrorHtml("اطلاعات مالی فروشنده ناقص است");
            }

            Map<Month, Integer> monthlySales = calculateMonthlySales();
            int currentMonthSales = monthlySales.getOrDefault(currentMonth, 0);
            int yearlySales = monthlySales.values().stream().mapToInt(Integer::intValue).sum();
            double monthlyAverage = monthlySales.isEmpty() ? 0 : yearlySales / (double) monthlySales.size();

            StringBuilder html = new StringBuilder();
            buildHeader(html);
            buildSellerIdentitySection(html);
            buildSalesSummarySection(html, currentMonthSales, yearlySales, monthlyAverage);
            buildMonthlySalesChart(html, monthlySales);
            buildTransactionDetails(html);
            closeHtml(html);

            return html.toString();

        } catch (Exception e) {
            return generateErrorHtml("خطای غیرمنتظره: " + e.getMessage());
        }
    }

    private void buildHeader(StringBuilder html) {
        html.append("<!DOCTYPE html>")
                .append("<html dir='rtl'>")
                .append("<head>")
                .append("<meta charset='UTF-8'>")
                .append("<title>گزارش جامع فروشنده</title>")
                .append("<script src='https://cdn.jsdelivr.net/npm/chart.js'></script>")
                .append("<style>")
                .append(getCssStyles())
                .append("</style>")
                .append("</head>")
                .append("<body>")
                .append("<div class='report-container'>")
                .append("<h1 class='report-title'>گزارش جامع عملکرد فروشنده</h1>");
    }

    private void buildSellerIdentitySection(StringBuilder html) {
        html.append("<div class='identity-section'>")
                .append("<h2>اطلاعات هویتی فروشنده</h2>")
                .append("<table>")
                .append("<tr><th>نام فروشگاه</th><td>").append(escapeHtml(seller.getStoreTitle())).append("</td></tr>")
                .append("<tr><th>نام کامل</th><td>")
                .append(escapeHtml(seller.getFirstname() + " " + seller.getLastname())).append("</td></tr>")
                .append("<tr><th>کد ملی</th><td>").append(escapeHtml(seller.getCodeMely())).append("</td></tr>")
                .append("<tr><th>استان</th><td>").append(escapeHtml(seller.getProvinceOfSale())).append("</td></tr>")
                .append("<tr><th>کد نمایندگی</th><td>").append(escapeHtml(seller.getAgencyCode())).append("</td></tr>")
                .append("</table>")
                .append("</div>");
    }

    private void buildSalesSummarySection(StringBuilder html, int currentMonthSales, int yearlySales,
            double monthlyAverage) {
        html.append("<div class='sales-summary'>")
                .append("<h2>خلاصه عملکرد فروش</h2>")
                .append("<div class='stats-grid'>")
                .append("<div class='stat-card'><h3>فروش ماه جاری</h3><p>").append(formatCurrency(currentMonthSales))
                .append("</p></div>")
                .append("<div class='stat-card'><h3>فروش کل سال</h3><p>").append(formatCurrency(yearlySales))
                .append("</p></div>")
                .append("<div class='stat-card'><h3>میانگین ماهانه</h3><p>")
                .append(formatCurrency((int) monthlyAverage)).append("</p></div>")
                .append("</div>")
                .append("</div>");
    }

    private void buildMonthlySalesChart(StringBuilder html, Map<Month, Integer> monthlySales) {
        html.append("<div class='chart-section'>")
                .append("<h2>نمودار فروش ماه‌های سال ").append(currentYear).append("</h2>")
                .append("<div class='chart-container'>")
                .append("<canvas id='monthlySalesChart'></canvas>")
                .append("</div>")
                .append("<script>")
                .append("const monthlyCtx = document.getElementById('monthlySalesChart').getContext('2d');")
                .append("new Chart(monthlyCtx, {")
                .append("type: 'bar',")
                .append("data: {")
                .append("labels: ").append(getMonthNames()).append(",")
                .append("datasets: [{")
                .append("label: 'مبلغ فروش',")
                .append("data: ").append(getMonthlySalesData(monthlySales)).append(",")
                .append("backgroundColor: 'rgba(54, 162, 235, 0.7)',")
                .append("borderColor: 'rgba(54, 162, 235, 1)',")
                .append("borderWidth: 1")
                .append("}]")
                .append("},")
                .append("options: {")
                .append("responsive: true,")
                .append("plugins: { legend: { rtl: true } },")
                .append("scales: {")
                .append("y: { beginAtZero: true, title: { display: true, text: 'مبلغ (تومان)' } },")
                .append("x: { title: { display: true, text: 'ماه‌های سال' } }")
                .append("}")
                .append("}")
                .append("});")
                .append("</script>")
                .append("</div>");
    }

    private void buildTransactionDetails(StringBuilder html) {
        Wallet wallet = seller.getWallet();
        html.append("<div class='transaction-section'>")
                .append("<h2>وضعیت مالی فعلی</h2>")
                .append("<p>موجودی کیف پول: <strong>").append(formatCurrency(wallet.getCash())).append("</strong></p>")
                .append("<p>تعداد تراکنش‌ها: <strong>")
                .append(wallet.getTransactions() != null ? wallet.getTransactions().size() : 0).append("</strong></p>")
                .append("</div>");
    }

    private Map<Month, Integer> calculateMonthlySales() {
        Map<Month, Integer> monthlySales = new EnumMap<>(Month.class);

        if (seller.getOrders() == null) {
            return monthlySales;
        }

        seller.getOrders().stream()
                .filter(order -> {
                    if (order == null || order.getLocalDateTime() == null) {
                        return false;
                    }
                    return true;
                })
                .filter(order -> {
                    if (order.getLocalDateTime().getYear() == currentYear) {
                        return true;
                    }
                    return false;
                })
                .forEach(order -> {
                    Month month = order.getLocalDateTime().getMonth();
                    int orderValue = order.getTotalPrice();
                    monthlySales.merge(month, orderValue, Integer::sum);
                });

        return monthlySales;
    }

    private String getMonthNames() {
        return Arrays.stream(Month.values())
                .map(month -> "\"" + getPersianMonthName(month) + "\"")
                .collect(Collectors.joining(",", "[", "]"));
    }

    private String getPersianMonthName(Month month) {
        Map<Month, String> persianMonths = new EnumMap<>(Month.class);
        persianMonths.put(Month.JANUARY, "فروردین");
        persianMonths.put(Month.FEBRUARY, "اردیبهشت");
        persianMonths.put(Month.MARCH, "خرداد");
        persianMonths.put(Month.APRIL, "تیر");
        persianMonths.put(Month.MAY, "مرداد");
        persianMonths.put(Month.JUNE, "شهریور");
        persianMonths.put(Month.JULY, "مهر");
        persianMonths.put(Month.AUGUST, "آبان");
        persianMonths.put(Month.SEPTEMBER, "آذر");
        persianMonths.put(Month.OCTOBER, "دی");
        persianMonths.put(Month.NOVEMBER, "بهمن");
        persianMonths.put(Month.DECEMBER, "اسفند");

        return persianMonths.getOrDefault(month, month.toString());
    }

    private String getMonthlySalesData(Map<Month, Integer> monthlySales) {
        return Arrays.stream(Month.values())
                .map(month -> String.valueOf(monthlySales.getOrDefault(month, 0)))
                .collect(Collectors.joining(",", "[", "]"));
    }

    private String formatCurrency(int amount) {
        return String.format("%,d تومان", amount);
    }

    private String escapeHtml(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }

    private String getCssStyles() {
        return "body { font-family: 'B Nazanin', Tahoma, sans-serif; padding: 20px; background-color: #f5f7fa; }" +
                ".report-container { max-width: 1000px; margin: 0 auto; background: white; padding: 30px; box-shadow: 0 0 20px rgba(0,0,0,0.1); border-radius: 10px; }"
                +
                ".report-title { text-align: center; color: #2c3e50; margin-bottom: 30px; border-bottom: 2px solid #3498db; padding-bottom: 15px; }"
                +
                ".identity-section, .sales-summary, .chart-section, .transaction-section { margin-bottom: 40px; padding: 20px; background: #fff; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.05); }"
                +
                "table { width: 100%; border-collapse: collapse; margin: 15px 0; }" +
                "th, td { border: 1px solid #ddd; padding: 12px; text-align: right; }" +
                "th { background-color: #3498db; color: white; }" +
                ".stats-grid { display: grid; grid-template-columns: repeat(3, 1fr); gap: 20px; margin-top: 20px; }" +
                ".stat-card { background: #f8f9fa; padding: 20px; border-radius: 8px; text-align: center; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }"
                +
                ".stat-card h3 { color: #2c3e50; margin-top: 0; }" +
                ".stat-card p { font-size: 24px; font-weight: bold; color: #3498db; margin-bottom: 0; }" +
                ".chart-container { width: 100%; height: 400px; margin: 20px 0; }";
    }

    private String generateErrorHtml(String message) {
        return "<!DOCTYPE html><html dir='rtl'><head><meta charset='UTF-8'><title>خطا</title>" +
                "<style>body { font-family: Tahoma; text-align: center; padding: 50px; color: #c0392b; }" +
                "h1 { margin-bottom: 20px; }</style></head>" +
                "<body><h1>خطا در تولید گزارش</h1>" +
                "<p>" + escapeHtml(message) + "</p>" +
                "</body></html>";
    }

    private void closeHtml(StringBuilder html) {
        html.append("</div></body></html>");
    }
}