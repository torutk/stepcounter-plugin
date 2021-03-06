package org.jenkinsci.plugins.stepcounter;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import hudson.util.FormValidation;

public class StepCounterSetting extends AbstractDescribableImpl<StepCounterSetting> {
    String encoding;
    String filePattern;
    String filePatternExclude;
    private String key;
    private static final String DEFAULT_FILEPATTERN = "";
    private static final String DEFAULT_FILEPATTERN_EXCLUDE = "";

    @DataBoundConstructor
    public StepCounterSetting(String encoding, String filePattern, String filePatternExclude, String key) {
        this.encoding = encoding;
        this.filePattern = filePattern;
        this.filePatternExclude = filePatternExclude;
        this.key = key;
    }

    public String getFilePattern() {
        if (filePattern == null || "".equals(filePattern)) {
            return DEFAULT_FILEPATTERN;
        }
        return filePattern;
    }

    public String getFilePatternExclude() {
        if (filePatternExclude == null || "".equals(filePatternExclude)) {
            return DEFAULT_FILEPATTERN_EXCLUDE;
        }
        return filePatternExclude;
    }

    public String getEncoding() {
        if (this.encoding != null && !"".equals(this.encoding)) {
            return this.encoding;
        } else {
            return System.getProperty("file.encoding", "UTF-8");
        }
    }

    @Extension
    public static class DescriptorImpl extends Descriptor<StepCounterSetting> {
        @Override
        public String getDisplayName() {
            return "";
        }

        /**
         * Performs on-the-fly validation of the form field 'name'.
         *
         * @param value
         *            This parameter receives the value that the user has typed.
         * @return Indicates the outcome of the validation. This is sent to the
         *         browser.
         */
        public FormValidation doCheckFilePattern(@QueryParameter String value) {
            return FormValidation.ok();
        }

        public FormValidation doCheckEncoding(@QueryParameter String value) {
            return FormValidation.ok();
        }

        public FormValidation doCheckKey(@QueryParameter String key) {
            if (key == null || "".equals(key)) {
                return FormValidation.error(Messages.errorCategoryRequired());
            }
            return FormValidation.ok();
        }
    }

    public String getKey() {
        return this.key;
    }
}
