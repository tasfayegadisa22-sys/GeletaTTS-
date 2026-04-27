private void copyAssetsToStorage() {
        File dataDir = new File(getExternalFilesDir(null), "geleta-tts-data");
        if (!dataDir.exists()) dataDir.mkdirs();

        try {
            // geleta-tts-data ፎልደር ውስጥ ያሉትን ፋይሎች በሙሉ በራሱ ይፈልጋል
            String[] files = getAssets().list("geleta-tts-data");
            
            if (files != null) {
                for (String filename : files) {
                    try (InputStream in = getAssets().open("geleta-tts-data/" + filename);
                         OutputStream out = new FileOutputStream(new File(dataDir, filename))) {
                        byte[] buffer = new byte[1024];
                        int read;
                        while ((read = in.read(buffer)) != -1) {
                            out.write(buffer, 0, read);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
