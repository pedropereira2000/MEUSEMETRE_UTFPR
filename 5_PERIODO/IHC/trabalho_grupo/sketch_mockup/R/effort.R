library(tidyverse)

e_bugs <- read_csv("effort.csv")
e_bugs$cDate <- as.Date(e_bugs$createdDate)
e_bugs$MonthOfCreation <- format(e_bugs$cDate, format = "%Y-%m")
e_bugs$week <- format(e_bugs$cDate, format = "%Y-%U")
dayOne <- min(e_bugs$cDate)
e_bugs$daysOfProject <- as.integer(e_bugs$cDate - dayOne)

pdf("daysToFix_perArea.pdf")
e_bugs %>% 
  filter(daysToFix != -1) %>%
  ggplot(aes(x=areaPath, y=daysToFix)) +
  geom_boxplot() +
  theme(axis.text.x=element_text(angle=90,hjust=1,vjust=0.5))
dev.off()

pdf("daysToFix_perEnvironment.pdf")
e_bugs %>% 
  filter(daysToFix != -1 & environment != 'NA') %>%
  ggplot(aes(x=environment, y=daysToFix)) +
  geom_violin() +
  geom_boxplot(width=0.1) +
  theme(axis.text.x=element_text(angle=90,hjust=1,vjust=0.5)) +
  stat_summary(fun=mean, geom="point", size=2, color="red")

e_bugs %>% 
  filter(daysToFix != -1 & environment != 'NA') %>%
  ggplot(aes(x=environment, y=daysToFix)) +
  coord_cartesian(ylim = c(0, 20)) + 
  geom_violin() +
  geom_boxplot(width=0.1) +
  theme(axis.text.x=element_text(angle=90,hjust=1,vjust=0.5)) +
  stat_summary(fun=mean, geom="point", size=2, color="red")

e_bugs %>% 
  filter(daysToFix != -1 & environment != 'NA') %>%
  ggplot(aes(x=environment, y=daysToFix)) +
  coord_cartesian(ylim = c(0, 10)) + 
  geom_violin() +
  geom_boxplot(width=0.1) +
  theme(axis.text.x=element_text(angle=90,hjust=1,vjust=0.5)) +
  stat_summary(fun=mean, geom="point", size=2, color="red")
dev.off()

#DATES
pdf("daysToFix_perDates.pdf")
e_bugs %>% 
  filter(daysToFix != -1) %>%
  ggplot(aes(x=MonthOfCreation, y=daysToFix)) +
  geom_boxplot() +
  theme(axis.text.x=element_text(angle=90,hjust=1,vjust=0.5))

e_bugs %>% 
  filter(daysToFix != -1) %>%
  ggplot(aes(x=week, y=daysToFix)) +
  geom_boxplot() +
  theme(axis.text.x=element_text(angle=90,hjust=1,vjust=0.5))

e_bugs %>% 
  filter(daysToFix != -1) %>%
  ggplot(aes(x=daysOfProject, y=daysToFix)) +
  geom_point() +
  geom_smooth()

e_bugs %>% 
  filter(daysToFix != -1) %>%
  ggplot(aes(x=daysOfProject, y=daysToFix)) +
  geom_point() +
  coord_cartesian(ylim = c(0, 50)) + 
  geom_smooth()

e_bugs %>% 
  filter(daysToFix != -1 & daysOfProject > 200) %>%
  ggplot(aes(x=daysOfProject, y=daysToFix)) +
  geom_point() +
  coord_cartesian(ylim = c(0, 20)) + 
  geom_smooth()

e_bugs %>% 
  filter(daysToFix != -1 & environment == 'development') %>%
  ggplot(aes(x=daysOfProject, y=daysToFix)) +
  ggtitle("development") +
  geom_point() +
  coord_cartesian(ylim = c(0, 30)) + 
  geom_smooth()

e_bugs %>% 
  filter(daysToFix != -1 & environment == 'qa') %>%
  ggplot(aes(x=daysOfProject, y=daysToFix)) +
  ggtitle("qa") +
  geom_point() +
  coord_cartesian(ylim = c(0, 30)) + 
  geom_smooth()

e_bugs %>% 
  filter(daysToFix != -1 & environment == 'staging') %>%
  ggplot(aes(x=daysOfProject, y=daysToFix)) +
  ggtitle("staging") +
  geom_point() +
  coord_cartesian(ylim = c(0, 30)) + 
  geom_smooth()

e_bugs %>% 
  filter(daysToFix != -1 & environment == 'production') %>%
  ggplot(aes(x=daysOfProject, y=daysToFix)) +
  ggtitle("production") +
  geom_point() +
  coord_cartesian(ylim = c(0, 30)) + 
  geom_smooth()

e_bugs %>% 
  filter(daysToFix != -1 & environment != 'NA') %>%
  ggplot(aes(x=daysOfProject, y=daysToFix, fill = environment)) +
  geom_point() +
  geom_smooth()

e_bugs %>% 
  filter(daysToFix != -1 & environment != 'NA' & daysOfProject > 300) %>%
  ggplot(aes(x=daysOfProject, y=daysToFix, fill = environment)) +
  geom_point() +
  geom_smooth()

e_bugs %>% 
  filter(daysToFix != -1 & environment != 'NA' & daysOfProject > 300) %>%
  ggplot(aes(x=daysOfProject, y=daysToFix, fill = environment)) +
  geom_point() +
  coord_cartesian(ylim = c(0, 40)) + 
  geom_smooth()
dev.off()

############

pdf("daysToFix_perArea_zoom.pdf")
e_bugs %>% 
  filter(daysToFix != -1) %>%
  ggplot(aes(x=areaPath, y=daysToFix)) +
  geom_boxplot() +
  coord_cartesian(ylim = c(0, 30)) + 
  theme(axis.text.x=element_text(angle=90,hjust=1,vjust=0.5))
dev.off()

pdf("daysToFix_closedBugs_per_area.pdf")
e_bugs %>% 
  filter(daysToFix != -1) %>%
  ggplot(aes(x=areaPath)) +
  geom_bar() +
  theme(axis.text.x=element_text(angle=90,hjust=1,vjust=0.5))
dev.off()  

pdf("daysToFix_severity_inst.pdf")
e_bugs %>% 
  filter(daysToFix != -1) %>%
  ggplot(aes(x=severity)) +
  geom_bar() +
  theme(axis.text.x=element_text(angle=90,hjust=1,vjust=0.5))
dev.off()  

pdf("daysToFix_per_severity.pdf")
e_bugs %>% 
  filter(daysToFix != -1) %>%
  ggplot(aes(x=severity, y=daysToFix)) +
  geom_violin() +
  geom_boxplot(width=0.1) +
  theme(axis.text.x=element_text(angle=90,hjust=1,vjust=0.5)) +
  stat_summary(fun=mean, geom="point", size=2, color="red")
dev.off()  

pdf("daysToFix_per_severity_zoom.pdf")
e_bugs %>% 
  filter(daysToFix != -1) %>%
  ggplot(aes(x=severity, y=daysToFix)) +
  coord_cartesian(ylim = c(0, 30)) + 
  geom_violin() +
  geom_boxplot(width=0.1) +
  theme(axis.text.x=element_text(angle=90,hjust=1,vjust=0.5)) +
  stat_summary(fun=mean, geom="point", size=2, color="red")
dev.off()  

