    private final static String consoleWatermark = "[+] Teleportation ";

    private final static String RESET = "\u001B[0m";
    private final static String RED = "\u001B[31m";
    private final static String GREEN = "\u001B[32m";
    private final static String YELLOW_BACKGROUND = "\u001B[43m";

    public void Output(String reason, Boolean watermark, String text)
    {
        String finalFormat = null;
        switch (reason)
        {
            default:
            case "Normal":
                finalFormat = (watermark ? consoleWatermark : "") + text;
                break;
            case "Success":
                finalFormat = (watermark ? consoleWatermark : "") + GREEN + text + RESET;
                break;
            case "Error":
                finalFormat = (watermark ? consoleWatermark : "") + RED + text + RESET;
                break;
            case "Warning":
                finalFormat = (watermark ? consoleWatermark : "") + YELLOW_BACKGROUND + text + RESET;
                break;
        }

        System.out.println(finalFormat);
    }
