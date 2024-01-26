package com.mftplus.automation;

import com.mftplus.automation.model.Organisation;
import com.mftplus.automation.model.Person;
import com.mftplus.automation.model.Section;
import com.mftplus.automation.model.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {
        log.info("Main Started");
//        CrudRepository<Organisation , Long> repository = new CrudRepository<>();

        Organisation organisation =
                Organisation
                        .builder()
                        .title("mftplus")
                        .build();

        Section edariSection =
                Section
                        .builder()
                        .title("Edari")
                        .build();

        Section maliSection =
                Section
                        .builder()
                        .title("Mali")
                        .build();

        User edariManager =
                User
                        .builder()
                        .build();

        Person edariManagerProfile =
                Person
                        .builder()
                        .nationalCode("EMP")
                        .build();

        User maliManager =
                User
                        .builder()
                        .build();

        Person maliManagerProfile =
                Person
                        .builder()
                        .nationalCode("MMP")
                        .build();

        User edariEmployee1 =
                User
                        .builder()
                        .build();
        Person edariEmployeeProfile1 =
                Person
                        .builder()
                        .nationalCode("EEP1")
                        .build();


        User edariEmployee2 =
                User
                        .builder()
                        .build();

        Person edariEmployeeProfile2 =
                Person
                        .builder()
                        .nationalCode("EEP2")
                        .build();

        User maliEmployee1 =
                User
                        .builder()
                        .build();

        Person maliEmployeeProfile1 =
                Person
                        .builder()
                        .nationalCode("MEP1")
                        .build();

        User maliEmployee2 =
                User
                        .builder()
                        .build();

        Person maliEmployeeProfile2 =
                Person
                        .builder()
                        .nationalCode("MEP2")
                        .build();

        User customer =
                User
                        .builder()
                        .build();

        Person customerProfile =
                Person
                        .builder()
                        .nationalCode("Aghaye")
                        .build();


        edariSection.setOrganisation(organisation);
        maliSection.setOrganisation(organisation);

        organisation.addSection(edariSection);
        organisation.addSection(maliSection);

//        repository.insert(organisation);

//        ProfileService profileService = new ProfileServiceImpl();
//        log.info("Profile Saved");
//        profileService.save(edariManagerProfile);
//        profileService.save(maliManagerProfile);

    }
}
