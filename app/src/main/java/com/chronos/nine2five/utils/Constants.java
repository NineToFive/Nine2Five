package com.chronos.nine2five.utils;

import com.chronos.nine2five.datastructures.Project;
import com.chronos.nine2five.datastructures.ProjectTask;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by user on 01/07/2016.
 */
public class Constants {

    public static final ProjectTask[] TASKS_LIST_1 = {new ProjectTask("A", "Programming"),
            new ProjectTask("B", "Documentation"),
            new ProjectTask("C", "Meeting"),
            new ProjectTask("D", "Professional Support"),
            new ProjectTask("E", "roman")};

    public static final ProjectTask[] TASKS_LIST_2 = {new ProjectTask("A2", "Programming 2"),
            new ProjectTask("B2", "Documentation 2"),
            new ProjectTask("C2", "Meeting 2"),
            new ProjectTask("D2", "Professional Support 2"),
            new ProjectTask("E2", "roman 2")};

    public static final Project[] PROJECTS_LIST = {new Project("PKO", "PKO", 0, Arrays.asList(TASKS_LIST_1)),
            new Project("UCG", "Unicredit De", 0, Arrays.asList(TASKS_LIST_2)),
            new Project("Nordea ME", "Nordea ME", 0, Arrays.asList(TASKS_LIST_1)),
            new Project("Nordea DK", "Nordea DK", 0, Arrays.asList(TASKS_LIST_2)),
            new Project("CEE", "Unicredit CEE", 0, Arrays.asList(TASKS_LIST_1)),
            new Project("Siam", "Siam", 0, Arrays.asList(TASKS_LIST_2))};


    public static final String TASKS_DIALOG = "tasksDialog";
}
