package com.chronos.nine2five.utils;

import com.chronos.nine2five.datastructures.Project;
import com.chronos.nine2five.datastructures.ProjectTask;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by user on 01/07/2016.
 */
public class Constants {

    public static final ProjectTask[] TASKS_LIST_1 = {new ProjectTask("A1", "Programming"),
            new ProjectTask("B1", "Documentation"),
            new ProjectTask("C1", "Meeting"),
            new ProjectTask("D1", "Professional Support"),
            new ProjectTask("E1", "roman")};

    public static final ProjectTask[] TASKS_LIST_2 = {new ProjectTask("A2", "Programming 2"),
            new ProjectTask("B2", "Documentation 2"),
            new ProjectTask("C2", "Meeting 2"),
            new ProjectTask("D2", "Professional Support 2"),
            new ProjectTask("E2", "roman 2")};

    public static final ProjectTask[] TASKS_LIST_3 = {new ProjectTask("A3", "Programming 3"),
            new ProjectTask("B3", "Documentation 3"),
            new ProjectTask("C3", "Meeting 3"),
            new ProjectTask("D3", "Professional Support 3"),
            new ProjectTask("E3", "roman 3")};

    public static final ProjectTask[] TASKS_LIST_4 = {new ProjectTask("A4", "Programming 4"),
            new ProjectTask("B4", "Documentation 4"),
            new ProjectTask("C4", "Meeting 4"),
            new ProjectTask("D4", "Professional Support 4"),
            new ProjectTask("E4", "roman 4")};
    public static final ProjectTask[] TASKS_LIST_5 = {new ProjectTask("A5", "Programming 5"),
            new ProjectTask("B5", "Documentation 5"),
            new ProjectTask("C5", "Meeting 5"),
            new ProjectTask("D5", "Professional Support 5"),
            new ProjectTask("E5", "roman 5")};
    public static final ProjectTask[] TASKS_LIST_6 = {new ProjectTask("A6", "Programming 6"),
            new ProjectTask("B6", "Documentation 6"),
            new ProjectTask("C6", "Meeting 6"),
            new ProjectTask("D6", "Professional Support 6"),
            new ProjectTask("E6", "roman 6")};

    public static final Project[] PROJECTS_LIST = {new Project("PKO", "PKO", Arrays.asList(TASKS_LIST_1)),
            new Project("UCG", "Unicredit De", Arrays.asList(TASKS_LIST_2)),
            new Project("Nordea ME", "Nordea ME", Arrays.asList(TASKS_LIST_3)),
            new Project("Nordea DK", "Nordea DK", Arrays.asList(TASKS_LIST_4)),
            new Project("CEE", "Unicredit CEE",Arrays.asList(TASKS_LIST_5)),
            new Project("Siam", "Siam", Arrays.asList(TASKS_LIST_6))};


    public static final String TASKS_DIALOG = "tasksDialog";

    public static final int INOUT_HEADER_ITEM = 0;
    public static final int INOUT_RECORD_ITEM = 1;
    public static final int INOUT_NUMBER_OF_TYPES = 2;

    public static final String DATE_ONLY_FORMAT = "dd/MM/yyyy";

}