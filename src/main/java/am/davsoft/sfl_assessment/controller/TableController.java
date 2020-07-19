package am.davsoft.sfl_assessment.controller;

import am.davsoft.sfl_assessment.core.controller.BaseController;
import am.davsoft.sfl_assessment.entity.CafeTable;
import am.davsoft.sfl_assessment.entity.User;
import am.davsoft.sfl_assessment.exception.TableNotFoundException;
import am.davsoft.sfl_assessment.exception.UserNotFoundException;
import am.davsoft.sfl_assessment.helper.PermissionEnum;
import am.davsoft.sfl_assessment.helper.TableState;
import am.davsoft.sfl_assessment.repository.TableRepository;
import am.davsoft.sfl_assessment.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.ResponseEntity.*;

/**
 * @author David Shahbazyan
 * @since Jul 19, 2020
 */
@RestController
@RequestMapping("/v1/tables")
public class TableController extends BaseController {
    private final TableRepository tableRepository;
    private final UserRepository userRepository;

    public TableController(TableRepository tableRepository, UserRepository userRepository) {
        this.tableRepository = tableRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public ResponseEntity loadAll() {
        return ok(this.tableRepository.findAll());
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody CafeTable cafeTable, HttpServletRequest request) {
        checkUserPermission((User) request.getUserPrincipal(), PermissionEnum.CREATE_TABLE);
        CafeTable saved = this.tableRepository.save(cafeTable);
        return created(
                ServletUriComponentsBuilder
                        .fromContextPath(request)
                        .path("/v1/tables/{id}")
                        .buildAndExpand(saved.getId())
                        .toUri())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Integer id) {
        return ok(this.tableRepository.findById(id).orElseThrow(TableNotFoundException::new));
    }

    @PutMapping("/{id}/assignTo")
    public ResponseEntity addProduct(@PathVariable("id") Integer id, HttpServletRequest request, @RequestBody User waiter) {
        checkUserPermission((User) request.getUserPrincipal(), PermissionEnum.ASSIGN_TABLE_TO_WAITER);
        CafeTable table = this.tableRepository.findById(id).orElseThrow(TableNotFoundException::new);
        table.setWaiter(waiter);
        this.tableRepository.save(table);
        return noContent().build();
    }

    @PutMapping("/{id}/changeState")
    public ResponseEntity changeState(@PathVariable("id") Integer id, @RequestBody TableState tableState) {
        CafeTable cafeTable = this.tableRepository.findById(id).orElseThrow(TableNotFoundException::new);
        switch (tableState) {
            case BUSY:
                return setBusy(cafeTable);
            case RESERVED:
                return setReserved(cafeTable);
            case FREE:
                return setFree(cafeTable);
        }
        return createMethodNotAllowedResponseEntity("Unsupported table state");
    }

    private ResponseEntity setReserved(CafeTable cafeTable) {
        if (cafeTable.isBusy()) {
            return createMethodNotAllowedResponseEntity("The table is busy");
        } else if (cafeTable.isReserved()) {
            return createMethodNotAllowedResponseEntity("The table is already reserved");
        } else {
            cafeTable.setIsReserved(true);
            this.tableRepository.save(cafeTable);
            return noContent().build();
        }
    }

    private ResponseEntity setBusy(CafeTable cafeTable) {
        if (cafeTable.isBusy()) {
            return createMethodNotAllowedResponseEntity("The table is already busy");
        } else if (cafeTable.isReserved()) {
            return createMethodNotAllowedResponseEntity("The table is reserved");
        } else {
            cafeTable.setIsBusy(true);
            this.tableRepository.save(cafeTable);
            return noContent().build();
        }
    }

    private ResponseEntity setFree(CafeTable cafeTable) {
        if (cafeTable.isBusy() || cafeTable.isReserved()) {
            cafeTable.setIsBusy(false);
            cafeTable.setIsReserved(false);
            this.tableRepository.save(cafeTable);
            return noContent().build();
        } else {
            return createMethodNotAllowedResponseEntity("The table is already free");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        if (isDeleteAllowed) {
            CafeTable cafeTable = this.tableRepository.findById(id).orElseThrow(TableNotFoundException::new);
            this.tableRepository.delete(cafeTable);
            return noContent().build();
        } else {
            return badRequest().build();
        }
    }
}
