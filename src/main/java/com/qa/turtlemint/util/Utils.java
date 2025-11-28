package com.qa.turtlemint.util;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Utils {
    public static String calculateDateToString(String format, String expiryValue) {


        LocalDateTime dateValue;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        int numOfDays = 5;


        if (expiryValue.equalsIgnoreCase("today")) {
            dateValue = LocalDateTime.now();
        } else if (expiryValue.contains("future")) {
            if (expiryValue.contains("-")) {
                numOfDays = Integer.parseInt(expiryValue.split("-")[1]);
            }

            dateValue = LocalDateTime.now().plusDays(numOfDays);

        } else if (expiryValue.contains("past")) {
            // dateValue=LocalDateTime.now().minusDays(30);
            if (expiryValue.contains("-")) {
                numOfDays = Integer.parseInt(expiryValue.split("-")[1]);
            }

            dateValue = LocalDateTime.now().minusYears(numOfDays);

        } else
            throw new RuntimeException("Enter valid choice of policy expiry");

//    	if(dateValue.format(formatter).startsWith("0"))
//    	{
//    		return dateValue.format(formatter).substring(1);
//    	}
        return dateValue.format(formatter);
    }

    public static Date calculateDate(String format, String date) {
        DateFormat formatter = new SimpleDateFormat(format);

        try {
            return formatter.parse(date);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    ////////////////--------------------NEW---------------////////////////

    public static void selectExactVisibleOption(WebDriver driver,
                                                By panelLocator,
                                                String optionText,
                                                int timeoutSeconds) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

        // 1) Open dropdown
//        WebElement toggle = wait.until(ExpectedConditions.elementToBeClickable(toggleLocator));
//        toggle.click();

        // small wait for panel
        sleep(50);

        // 2) Find visible panel
        WebElement panel = wait.until(d -> {
            List<WebElement> panels = d.findElements(panelLocator);
            for (WebElement p : panels) if (p.isDisplayed()) return p;
            return null;
        });

        // 3) Find candidate nodes inside panel by exact text
        List<WebElement> candidates = panel.findElements(By.xpath(".//*[normalize-space(text()) = \"" + escape(optionText) + "\"]"));

        // 4) Filter to displayed + nonzero size
        WebElement chosen = null;
        for (WebElement c : candidates) {
            if (!c.isDisplayed()) continue;
            if (!hasSize(js, c)) continue;

            // prefer clickable ancestor (some UIs attach click handler to parent)
            WebElement clickable = findClickableAncestor(c);
            if (clickable == null) clickable = c;

            // 5) Verify elementFromPoint at center resolves to this clickable (diagnostic)
            Map<String, Number> rect = (Map<String, Number>) js.executeScript(
                    "var r = arguments[0].getBoundingClientRect();" +
                            "return {x: Math.round(r.left + r.width/2), y: Math.round(r.top + r.height/2)};", clickable);
            Number cx = rect.get("x"), cy = rect.get("y");

            // element at point
            String atPointHtml = (String) js.executeScript(
                    "var el = document.elementFromPoint(arguments[0], arguments[1]); return el ? el.outerHTML : null;",
                    cx, cy);

            // if elementFromPoint contains optionText or the clickable itself, accept it
            if (atPointHtml != null && (atPointHtml.contains(optionText) || atPointHtml.contains(getNodeName(clickable)))) {
                chosen = clickable;
                break;
            }
            // else still accept candidate if displayed and sizes ok — sometimes elementFromPoint returns inner <span>.
            chosen = clickable;
            break;
        }

        if (chosen == null) {
            throw new NoSuchElementException("Visible option not found inside visible panel for text: " + optionText);
        }

        // 6) Click robustly: normal -> JS -> coordinates
        try {
            wait.until(ExpectedConditions.elementToBeClickable(chosen));
            chosen.click();
            return;
        } catch (Exception e1) {
            try {
                js.executeScript("arguments[0].click();", chosen);
                return;
            } catch (Exception e2) {
                // final fallback: click by viewport coordinates
                Map<String, Number> rect = (Map<String, Number>) js.executeScript(
                        "var r = arguments[0].getBoundingClientRect(); return {x: Math.round(r.left + window.pageXOffset + r.width/2), y: Math.round(r.top + window.pageYOffset + r.height/2)};",
                        chosen);
                int x = rect.get("x").intValue();
                int y = rect.get("y").intValue();

                try {
                    // reset mouse and click
                    new Actions(driver).moveByOffset(-10000, -10000).perform();
                } catch (Exception ignored) {}
                new Actions(driver).moveByOffset(x, y).click().perform();
                try { new Actions(driver).moveByOffset(-x, -y).perform(); } catch (Exception ignored) {}
                return;
            }
        }
    }

    // helpers
    private static boolean hasSize(JavascriptExecutor js, WebElement e) {
        try {
            Map<String, Number> rect = (Map<String, Number>) js.executeScript(
                    "var r = arguments[0].getBoundingClientRect(); return {w: r.width, h: r.height};", e);
            return rect.get("w").intValue() > 0 && rect.get("h").intValue() > 0;
        } catch (Exception ex) { return false; }
    }

    private static WebElement findClickableAncestor(WebElement el) {
        WebElement current = el;
        try {
            while (current != null) {
                String role = current.getAttribute("role");
                String onClick = current.getAttribute("onclick");
                String cls = current.getAttribute("class");
                if ((role != null && (role.equals("option") || role.equals("button"))) ||
                        (onClick != null && !onClick.isEmpty()) ||
                        (cls != null && cls.contains("ant-select-item") || (cls != null && cls.contains("option")))) {
                    return current;
                }
                WebElement parent = current.findElement(By.xpath(".."));
                if (parent == current) break;
                current = parent;
            }
        } catch (Exception ignored) {}
        return null;
    }

    private static String getNodeName(WebElement e) {
        try { return e.getTagName(); } catch (Exception ex) { return ""; }
    }

    private static String escape(String s) { return s.replace("\"", "\\\""); }

    private static void sleep(long ms) { try { Thread.sleep(ms); } catch (InterruptedException ignored) {} }

}